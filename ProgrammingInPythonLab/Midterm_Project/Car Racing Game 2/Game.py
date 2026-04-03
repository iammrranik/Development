import pygame
import random
from Db.FileLoader import FileLoader
import Settings
from Db.Paths import *
from Utils import display_score, get_high_scores, update_high_scores
from Entities.CarPlayer import CarPlayer
from Entities.CarObstacle import CarObstacle

class Game:
    """Main class to manage game state and run the main game loop."""

    def __init__(self):
        pygame.init()
        pygame.mixer.init()
        self.clock = pygame.time.Clock()
        
        # initialize font objects after pygame.init() to avoid "font not initialized" error
        Settings.init_fonts()

        self.icon_image = FileLoader.load_single_icon(icon_image_path)
        if self.icon_image:
            pygame.display.set_icon(self.icon_image)
        
        # Create Window and set caption
        self.screen = pygame.display.set_mode((Settings.screen_width, Settings.screen_height))
        pygame.display.set_caption(Settings.GAME_TITLE)


        self.bg_home_image = FileLoader.load_single_image(bg_home_image_path, Settings.TRANSPARENT_APPHA_MEDIUM)
        if self.bg_home_image:
            self.bg_home_image = pygame.transform.scale(self.bg_home_image, (Settings.screen_width, Settings.screen_height))
        
        self.bg_hiscores_image = FileLoader.load_single_image(hiscore_bg_image_path, Settings.TRANSPARENT_APPHA_HIGH)
        if self.bg_hiscores_image:
            self.bg_hiscores_image = pygame.transform.scale(self.bg_hiscores_image, (Settings.screen_width, Settings.screen_height))

        self.bg_gameover_image = FileLoader.load_single_image(game_over_bg_image_path, Settings.TRANSPARENT_APPHA_HIGH)
        if self.bg_gameover_image:
            self.bg_gameover_image = pygame.transform.scale(self.bg_gameover_image, (Settings.screen_width, Settings.screen_height))

        self.road_image = FileLoader.load_single_image(road_image_path)
        if self.road_image:
            self.road_image = pygame.transform.scale(self.road_image, (Settings.screen_width, Settings.screen_height))

        self.car_models = self._load_car_models()

        self.high_scores = get_high_scores()
        # store the highest score from existing data, default 0 when no high score rows yet
        if self.high_scores:
            self.high_score = self.high_scores[0][0]
        else:
            self.high_score = 0

        self.game_state = Settings.WELCOME

        self.play_button = pygame.Rect(Settings.screen_width // 2 - 100, Settings.HOME_PLAY_Y, 200, 50)
        self.high_score_button = pygame.Rect(Settings.screen_width // 2 - 100, Settings.HOME_HIGHSCORE_Y, 200, 50)
        self.quit_button = pygame.Rect(Settings.screen_width // 2 - 100, Settings.HOME_QUIT_Y, 200, 50)

        self.game_over_play_again_btn = pygame.Rect(Settings.screen_width // 2 - 110, Settings.screen_height * 2 // 3 + 20, 220, 50)
        self.game_over_home_btn = pygame.Rect(Settings.screen_width // 2 - 110, Settings.screen_height * 2 // 3 + 90, 220, 50)

        self.reset_game()

    def _load_car_models(self):
        # Attempt to load all car models from disk; fall back to a solid color rectangle if missing
        models = FileLoader.load_multiple_images(cars_image_path)
        if not models:
            fallback = pygame.Surface((Settings.CAR_WIDTH, Settings.CAR_HEIGHT), pygame.SRCALPHA)
            # Use configured color constants from Settings for consistency
            fallback.fill((Settings.RED, 255))
            return {"fallback": fallback}
        return models

    def _random_car_image(self):
        return random.choice(list(self.car_models.values()))

    def _get_obstacle_spawn_count(self):
        # 1-5: one obstacle per spawn cycle, 6-10: two obstacles, 11+: three obstacles
        if self.level <= 5:
            return 1
        if self.level <= 10:
            return 2
        return 3

    def run(self):
        while self.game_state != Settings.EXIT_GAME:
            if self.game_state == Settings.WELCOME:
                self.display_welcome_screen()
            elif self.game_state == Settings.PLAYING_GAME:
                self.game_loop()
            elif self.game_state == Settings.GAME_OVER:
                self.display_game_over_screen()
            elif self.game_state == Settings.HIGH_SCORES:
                self.display_high_scores_screen()

        pygame.quit()

    def display_welcome_screen(self):
        try:
            pygame.mixer.music.load(charmer_music_path)
            pygame.mixer.music.play(-1)  # Loop background music indefinitely
        except Exception as e:
            print(f"Crash sound failed: {e}")
        
        while self.game_state == Settings.WELCOME:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    self.game_state = Settings.EXIT_GAME
                elif event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_ESCAPE:
                        self.game_state = Settings.EXIT_GAME
                    else:
                        self.reset_game()
                        self.game_state = Settings.PLAYING_GAME
                elif event.type == pygame.MOUSEBUTTONDOWN and event.button == 1:
                    if self.play_button.collidepoint(event.pos):
                        self.reset_game()
                        self.game_state = Settings.PLAYING_GAME
                    elif self.high_score_button.collidepoint(event.pos):
                        self.game_state = Settings.HIGH_SCORES
                    elif self.quit_button.collidepoint(event.pos):
                        self.game_state = Settings.EXIT_GAME

            self.screen.blit(self.bg_home_image, (0, 0))

            title_color = (random.randint(0, 255), random.randint(0, 255), random.randint(0, 255))
            title_text = Settings.font_large.render("CAR RACING GAME", True, title_color)
            self.screen.blit(title_text, (Settings.screen_width // 2 - title_text.get_width() // 2, Settings.HOME_TITLE_Y))

            click_text = Settings.font_medium.render("Press any key to start / Click a button", True, Settings.WHITE)
            self.screen.blit(click_text, (Settings.screen_width // 2 - click_text.get_width() // 2, Settings.HOME_TITLE_Y + 80))
            
            press_text = Settings.font_small.render("Press ESC to quit the game", True, Settings.WHITE)
            self.screen.blit(press_text, (Settings.screen_width // 2 - press_text.get_width() // 2, Settings.HOME_TITLE_Y + 110))

            pygame.draw.rect(self.screen, Settings.GREEN, self.play_button)
            play_text = Settings.font_medium.render("Play", True, Settings.BLACK)
            self.screen.blit(play_text, (self.play_button.centerx - play_text.get_width() // 2, self.play_button.centery - play_text.get_height() // 2))

            pygame.draw.rect(self.screen, Settings.YELLOW, self.high_score_button)
            high_score_text = Settings.font_medium.render("High Scores", True, Settings.BLACK)
            self.screen.blit(high_score_text, (self.high_score_button.centerx - high_score_text.get_width() // 2, self.high_score_button.centery - high_score_text.get_height() // 2))

            pygame.draw.rect(self.screen, Settings.RED, self.quit_button)
            quit_text = Settings.font_medium.render("Quit", True, Settings.BLACK)
            self.screen.blit(quit_text, (self.quit_button.centerx - quit_text.get_width() // 2, self.quit_button.centery - quit_text.get_height() // 2))

            # Add gameplay instructions on the home screen
            instr_1 = Settings.font_small.render("Press LEFT arrow (<-) to move to left lane", True, Settings.WHITE)
            instr_2 = Settings.font_small.render("Press RIGHT arrow (->) to move to right lane", True, Settings.WHITE)
            instr_3 = Settings.font_small.render("*** Avoid obstacles and score points ***", True, Settings.WHITE)
            self.screen.blit(instr_1, (Settings.screen_width // 2 - instr_1.get_width() // 2, Settings.HOME_QUIT_Y + 120))
            self.screen.blit(instr_2, (Settings.screen_width // 2 - instr_2.get_width() // 2, Settings.HOME_QUIT_Y + 150))
            self.screen.blit(instr_3, (Settings.screen_width // 2 - instr_3.get_width() // 2, Settings.HOME_QUIT_Y + 180))

            pygame.display.flip()
            self.clock.tick(Settings.MENU_FPS)

    def game_loop(self):
        while self.game_state == Settings.PLAYING_GAME:
            delta_ms = self.clock.tick(Settings.FPS)

            self._process_events()
            self._update_game_state(delta_ms)
            self._draw_game_scene()

    def _process_events(self):
        """Read player or system input each frame."""
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                self.game_state = Settings.EXIT_GAME
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_LEFT:
                    self.player_car.move("LEFT")
                elif event.key == pygame.K_RIGHT:
                    self.player_car.move("RIGHT")

    def _update_game_state(self, delta_ms: int):
        """Move road, spawn obstacles, update score/level, detect collision."""
        self.level_timer += delta_ms
        if self.level_timer >= Settings.LEVEL_INTERVAL_MS:
            pygame.mixer.music.load(beep_sound_path)
            pygame.mixer.music.play()
            levels_gained = self.level_timer // Settings.LEVEL_INTERVAL_MS
            self.level += levels_gained
            self.level_timer %= Settings.LEVEL_INTERVAL_MS

        self.spawn_timer += delta_ms
        if self.spawn_timer >= Settings.SPAWN_OBSTACLE_EVERY_MS:
            # Keep spawn timer synced to frame time
            self.spawn_timer %= Settings.SPAWN_OBSTACLE_EVERY_MS

            spawn_count = self._get_obstacle_spawn_count()
            lanes_available = list(Settings.LANE_X_POSITIONS)
            random.shuffle(lanes_available)

            for lane_x in lanes_available[:spawn_count]:
                if len(self.obstacles) >= Settings.MAX_OBSTACLES:
                    break
                self.obstacles.append(CarObstacle(self._random_car_image(), lane_x=lane_x))

        obstacle_speed = min(
            Settings.INITIAL_OBSTACLE_SPEED + self.level * Settings.OBSTACLE_SPEED_INCREMENT,
            Settings.OBSTACLE_MAX_SPEED,
        )

        self.road_y = (self.road_y + Settings.ROAD_SPEED) % Settings.screen_height

        for obstacle in list(self.obstacles):
            obstacle.speed = obstacle_speed
            obstacle.update(delta_ms)

            if obstacle.is_off_screen():
                self.obstacles.remove(obstacle)
                self.score += 10 * self.level
                continue

            if obstacle.get_rect().colliderect(self.player_car.get_rect()):
                # Play crash sound when hitting an obstacle
                try:
                    pygame.mixer.music.load(car_crash_sound_path)
                    pygame.mixer.music.play()
                except Exception as e:
                    print(f"Crash sound failed: {e}")

                self.high_score = max(self.high_score, self.score)
                self.game_state = Settings.GAME_OVER
                self.high_scores = update_high_scores(self.score, self.level, self.high_score)
                self.high_score = self.high_scores[0][0] if self.high_scores else self.high_score
    
    def _draw_game_scene(self):
        """Clear screen, draw background, road, player, obstacles, score."""
        self.screen.fill(Settings.ROAD_BACKGROUND)

        if self.road_image:
            self.screen.blit(self.road_image, (0, self.road_y - Settings.screen_height))
            self.screen.blit(self.road_image, (0, self.road_y))

        self.player_car.draw(self.screen)

        for obstacle in self.obstacles:
            obstacle.draw(self.screen)

        display_score(self.screen, self.score, self.level, self.high_score)
        pygame.display.flip()
    
    def display_game_over_screen(self):
        while self.game_state == Settings.GAME_OVER:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    self.game_state = Settings.EXIT_GAME
                elif event.type == pygame.MOUSEBUTTONDOWN:
                    if self.game_over_play_again_btn.collidepoint(event.pos):
                        self.reset_game()
                        self.game_state = Settings.PLAYING_GAME
                    elif self.game_over_home_btn.collidepoint(event.pos):
                        self.game_state = Settings.WELCOME
                elif event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_ESCAPE:
                        self.game_state = Settings.WELCOME
                    else:
                        self.reset_game()
                        self.game_state = Settings.PLAYING_GAME

            if self.bg_gameover_image:
                self.screen.blit(self.bg_gameover_image, (0, 0))

            overlay = pygame.Surface((Settings.screen_width, Settings.screen_height), pygame.SRCALPHA)
            overlay.fill(Settings.OVERLAY_SEMI_TRANSPARENT)
            self.screen.blit(overlay, (0, 0))
            

            game_over_text = Settings.font_large.render("GAME OVER", True, Settings.RED)
            final_score_text = Settings.font_medium.render(f"Final Score: {self.score}", True, Settings.WHITE)
            high_score_text = Settings.font_medium.render(f"High Score: {self.high_score}", True, Settings.WHITE)

            self.screen.blit(game_over_text, (Settings.screen_width // 2 - game_over_text.get_width() // 2, Settings.screen_height // 4 - 80))
            self.screen.blit(final_score_text, (Settings.screen_width // 2 - final_score_text.get_width() // 2, Settings.screen_height // 2 - 100))
            self.screen.blit(high_score_text, (Settings.screen_width // 2 - high_score_text.get_width() // 2, Settings.screen_height // 2 + 45 - 100))

            pygame.draw.rect(self.screen, Settings.GREEN, self.game_over_play_again_btn)
            btn_text = Settings.font_medium.render("Play Again", True, Settings.BLACK)
            self.screen.blit(btn_text, (self.game_over_play_again_btn.centerx - btn_text.get_width() // 2, self.game_over_play_again_btn.centery - btn_text.get_height() // 2))

            pygame.draw.rect(self.screen, Settings.BLUE, self.game_over_home_btn)
            home_text = Settings.font_medium.render("Home", True, Settings.BLACK)
            self.screen.blit(home_text, (self.game_over_home_btn.centerx - home_text.get_width() // 2, self.game_over_home_btn.centery - home_text.get_height() // 2))
            
            press_text = Settings.font_medium.render("Press any key to play again or click a button", True, Settings.WHITE)
            self.screen.blit(press_text, (Settings.screen_width // 2 - press_text.get_width() // 2, Settings.screen_height * 2 // 3 - 40))
            end_text = Settings.font_small.render("Press ESC to return to home screen", True, Settings.WHITE)
            self.screen.blit(end_text, (Settings.screen_width // 2 - end_text.get_width() // 2, Settings.screen_height * 2 // 3 + 170))

            pygame.display.flip()
            self.clock.tick(Settings.FPS)

    def display_high_scores_screen(self):
        while self.game_state == Settings.HIGH_SCORES:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    self.game_state = Settings.EXIT_GAME
                elif event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_ESCAPE:
                        self.game_state = Settings.WELCOME
                elif event.type == pygame.MOUSEBUTTONDOWN and event.button == 1:
                    if self.game_over_home_btn.collidepoint(event.pos):
                        self.game_state = Settings.WELCOME

            if self.bg_hiscores_image:
                self.screen.blit(self.bg_hiscores_image, (0, 0,))
            title_text = Settings.font_large.render("HIGH SCORES", True, Settings.GREEN)
            self.screen.blit(title_text, (Settings.screen_width // 2 - title_text.get_width() // 2, 60))

            highscores = get_high_scores()
            for i, (score, level, top_score) in enumerate(highscores):
                score_line = Settings.font_medium.render(
                    f"{i + 1}. Score: {score} | Level: {level} | Top: {top_score}", True, Settings.WHITE
                )
                self.screen.blit(score_line, (Settings.screen_width // 2 - score_line.get_width() // 2, 160 + i * 40))

            pygame.draw.rect(self.screen, Settings.BLUE, self.game_over_home_btn)
            back_text = Settings.font_medium.render("Back", True, Settings.BLACK)
            self.screen.blit(back_text, (self.game_over_home_btn.centerx - back_text.get_width() // 2, self.game_over_home_btn.centery - back_text.get_height() // 2))
            press_text = Settings.font_small.render("Press ESC or click Back to return", True, Settings.WHITE)
            self.screen.blit(press_text, (Settings.screen_width // 2 - press_text.get_width() // 2, Settings.screen_height - 50))

            pygame.display.flip()
            self.clock.tick(Settings.FPS)

    def reset_game(self):
        self.player_car = CarPlayer(self._random_car_image())
        self.obstacles = []
        self.score = 0
        self.level = 1
        self.level_timer = 0
        self.spawn_timer = 0
        self.road_y = 0


