import pygame
import random

from Db.FileLoader import FileLoader
from Settings import (
    GAME_TITLE,
    LANE_X_POSITIONS,
    screen_width,
    screen_height,
    WELCOME,
    PLAYING_GAME,
    GAME_OVER,
    EXIT_GAME,
    font_large,
    font_medium,
    GREEN,
    BLACK,
    RED,
    WHITE,
    ROAD_BACKGROUND,
    OVERLAY_SEMI_TRANSPARENT,
    FPS,
    INITIAL_OBSTACLE_SPEED,
    OBSTACLE_SPEED_INCREMENT,
    ROAD_SPEED,
    CAR_WIDTH,
    CAR_HEIGHT,
    LANE_COUNTS,
    SPAWN_OBSTACLE_EVERY_MS,
    LEVEL_INTERVAL_MS,
    MAX_OBSTACLES,
    PLAY_AGAIN_BTN_SIZE,
    PLAY_AGAIN_BTN_OFFSET_Y,
    ROAD_START_Y,
)
from Db.Paths import cars_image_path, road_image_path
from Utils import display_score
from Entities.CarPlayer import CarPlayer
from Entities.CarObstacle import CarObstacle


class Game:
    """Main class to manage game state and run the main game loop."""

    def __init__(self):
        pygame.init()
        self.clock = pygame.time.Clock()
        pygame.display.set_caption(GAME_TITLE)
        self.screen = pygame.display.set_mode((screen_width, screen_height))

        self.road_image = FileLoader.load_single_image(road_image_path)
        if self.road_image:
            self.road_image = pygame.transform.scale(self.road_image, (screen_width, screen_height))

        self.car_models = self._load_car_models()

        self.game_state = WELCOME
        self.reset_game()

    def _load_car_models(self):
        # Attempt to load all car models from disk; fall back to a solid color rectangle if missing
        models = FileLoader.load_multiple_images(cars_image_path)
        if not models:
            fallback = pygame.Surface((CAR_WIDTH, CAR_HEIGHT), pygame.SRCALPHA)
            # Use configured color constants from Settings for consistency
            fallback.fill((*RED, 255))
            return {"fallback": fallback}
        return models

    def _random_car_image(self):
        return random.choice(list(self.car_models.values()))

    def run(self):
        while self.game_state != EXIT_GAME:
            if self.game_state == WELCOME:
                self.display_welcome_screen()
            elif self.game_state == PLAYING_GAME:
                self.game_loop()
            elif self.game_state == GAME_OVER:
                self.display_game_over_screen()

        pygame.quit()

    def display_welcome_screen(self):
        while self.game_state == WELCOME:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    self.game_state = EXIT_GAME
                elif event.type == pygame.KEYDOWN:
                    self.reset_game()
                    self.game_state = PLAYING_GAME

            self.screen.fill(BLACK)
            title_text = font_large.render("CAR RACING GAME", True, WHITE)
            prompt_text = font_medium.render("Press any key to start", True, GREEN)

            self.screen.blit(title_text, (screen_width // 2 - title_text.get_width() // 2, screen_height // 3))
            self.screen.blit(prompt_text, (screen_width // 2 - prompt_text.get_width() // 2, screen_height // 2))

            pygame.display.flip()
            self.clock.tick(FPS)

    def game_loop(self):
        while self.game_state == PLAYING_GAME:
            delta_ms = self.clock.tick(FPS)

            self._process_events()
            self._update_game_state(delta_ms)
            self._draw_game_scene()

        # Return to top-level run() state manager

    def _process_events(self):
        """Read player or system input each frame."""
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                self.game_state = EXIT_GAME
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_LEFT:
                    self.player_car.move("LEFT")
                elif event.key == pygame.K_RIGHT:
                    self.player_car.move("RIGHT")

    def _update_game_state(self, delta_ms: int):
        """Move road, spawn obstacles, update score/level, detect collision."""
        self.level_timer += delta_ms
        if self.level_timer >= LEVEL_INTERVAL_MS:
            self.level += 1
            self.level_timer = 0

        self.spawn_timer += delta_ms
        if self.spawn_timer >= SPAWN_OBSTACLE_EVERY_MS:
            self.spawn_timer = 0
            if len(self.obstacles) < MAX_OBSTACLES:
                self.obstacles.append(CarObstacle(self._random_car_image()))

        obstacle_speed = INITIAL_OBSTACLE_SPEED + self.level * OBSTACLE_SPEED_INCREMENT
        self.road_y = (self.road_y + ROAD_SPEED) % screen_height

        for obstacle in list(self.obstacles):
            obstacle.speed = obstacle_speed
            obstacle.update(delta_ms)

            if obstacle.get_rect().colliderect(self.player_car.get_rect()):
                self.game_state = GAME_OVER

        self.obstacles = [o for o in self.obstacles if not o.is_off_screen()]
        self.score += 1  # simple linear score progression

    def _draw_game_scene(self):
        """Clear screen, draw background, road, player, obstacles, score."""
        self.screen.fill(ROAD_BACKGROUND)

        if self.road_image:
            self.screen.blit(self.road_image, (0, self.road_y - screen_height))
            self.screen.blit(self.road_image, (0, self.road_y))

        self.player_car.draw(self.screen)

        for obstacle in self.obstacles:
            obstacle.draw(self.screen)

        display_score(self.screen, self.score, self.level)
        pygame.display.flip()
    def display_game_over_screen(self):
        while self.game_state == GAME_OVER:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    self.game_state = EXIT_GAME
                elif event.type == pygame.MOUSEBUTTONDOWN:
                    if self.play_again_btn.collidepoint(event.pos):
                        self.reset_game()
                        self.game_state = PLAYING_GAME

            overlay = pygame.Surface((screen_width, screen_height), pygame.SRCALPHA)
            overlay.fill(OVERLAY_SEMI_TRANSPARENT)
            self.screen.blit(overlay, (0, 0))

            game_over_text = font_large.render("GAME OVER", True, RED)
            final_score_text = font_medium.render(f"Final Score: {self.score}", True, WHITE)

            self.screen.blit(game_over_text, (screen_width // 2 - game_over_text.get_width() // 2, screen_height // 4))
            self.screen.blit(final_score_text, (screen_width // 2 - final_score_text.get_width() // 2, screen_height // 2))

            self.play_again_btn = pygame.Rect(screen_width // 2 - 110, screen_height * 2 // 3, 220, 50)
            pygame.draw.rect(self.screen, GREEN, self.play_again_btn)
            btn_text = font_medium.render("Play Again", True, BLACK)
            self.screen.blit(btn_text, (self.play_again_btn.centerx - btn_text.get_width() // 2, self.play_again_btn.centery - btn_text.get_height() // 2))

            pygame.display.flip()
            self.clock.tick(FPS)

    def reset_game(self):
        self.player_car = CarPlayer(self._random_car_image())
        self.obstacles = []
        self.score = 0
        self.level = 1
        self.level_timer = 0
        self.spawn_timer = 0
        self.road_y = 0


