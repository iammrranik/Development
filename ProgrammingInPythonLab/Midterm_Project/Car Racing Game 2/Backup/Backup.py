'''
pygame - A popular library for creating games and multimedia applications in Python.
random - A built-in module for generating random numbers and performing random operations.
os - A built-in module for interacting with the operating system, such as file and directory operations.
'''

import os
import pygame
import random

# Initialize Pygame
pygame.init()

# Game Colors
WHITE = (255, 255, 255)
RED = (255, 0, 0)
GREEN = (0, 255, 0)
BLUE = (0, 0, 255)
BLACK = (0, 0, 0)

# Window Settings
screen_width = 840
screen_height = 650
screen = pygame.display.set_mode((screen_width, screen_height))
pygame.display.set_caption("Car Racing Game")

# Load Road Image
road_image_path = "./Assets/Road/road1.png"
road_image = pygame.image.load(road_image_path).convert_alpha()

# Car Image Path
cars_image_path = "./Assets/CarModels"

# Load all car images from the path and store them in a dictionary
car_models = {}
for file_name in os.listdir(cars_image_path):
    if file_name.endswith(".png"):
        path = os.path.join(cars_image_path, file_name)
        car_name = file_name.replace(".png", "")
        car_models[car_name] = pygame.image.load(path).convert_alpha()

print("Loaded cars:", list(car_models.keys()))

# Car Properties
player_car = random.choice(list(car_models.values()))
CAR_WIDTH = 60
CAR_HEIGHT = 100
player_car = pygame.transform.scale(player_car, (CAR_WIDTH, CAR_HEIGHT))

# Lane Properties and Initial Car Position
LANE_X_POSITIONS = [200, 324, 454, 581]
LANE_COUNTS = len(LANE_X_POSITIONS)
currrent_lane = random.randint(0, LANE_COUNTS - 1)  # Start in any lane randomly
car_x = LANE_X_POSITIONS[currrent_lane]
car_y = screen_height - CAR_HEIGHT - 20  # Position the car near the bottom of the screen

# Obstacle Properties
OBSTACLE_WIDTH = 60
OBSTACLE_HEIGHT = 100
obstacle_speed = 5
obstacles = []

# Road Properties
road_y = 0
road_speed = 5

# Game Variables
score = 0
level = 1
level_timer = 0  # Tracks elapsed time in milliseconds
level_interval = 5000  # 5 seconds in milliseconds

clock = pygame.time.Clock()

font_small = pygame.font.Font(None, 24)
font_medium = pygame.font.Font(None, 36)
font_large = pygame.font.Font(None, 72)



# Function to generate a random obstacle
def get_random_obstacle():
    car = random.choice(list(car_models.values()))
    return pygame.transform.scale(car, (OBSTACLE_WIDTH, OBSTACLE_HEIGHT))

def display_score(score, level):
    score_text = font_small.render(f"Score: {score}", True, WHITE)
    level_text = font_small.render(f"Level: {level}", True, WHITE)
    screen.blit(score_text, (10, 10))
    screen.blit(level_text, (10, 30))

def display_game_over() -> pygame.Rect:
    
    overlay = pygame.Surface((screen_width, screen_height), pygame.SRCALPHA)
    overlay.fill((0, 0, 0, 128))  # Semi-transparent black
    screen.blit(overlay, (0, 0))
    
    game_over_text = font_large.render("GAME OVER", True, RED)
    screen.blit(game_over_text, (screen_width // 2 - game_over_text.get_width() // 2, screen_height // 3))
    
    score_text = font_medium.render(f"Final Score: {score}", True, WHITE)
    screen.blit(score_text, (screen_width // 2 - score_text.get_width() // 2, screen_height // 2))
    
    play_again_btn = pygame.Rect(screen_width // 2 - 100, screen_height * 2 // 3, 200, 50)
    pygame.draw.rect(screen, GREEN, play_again_btn)
    play_again_text = font_medium.render("Play Again", True, BLACK)
    screen.blit(play_again_text, (play_again_btn.centerx - play_again_text.get_width() // 2, play_again_btn.centery - play_again_text.get_height() // 2))
    return play_again_btn

def reset_game():
    global currrent_lane, car_x, car_y, obstacles, score, game_state, road_y
    currrent_lane = LANE_COUNTS // 2
    car_x = LANE_X_POSITIONS[currrent_lane]
    car_y = screen_height - CAR_HEIGHT - 20
    obstacles = []
    score = 0
    game_state = PLAYING_GAME
    road_y = 0

def display_welcome_screen(game_state):
    """
    Show welcome screen until player presses any key, then start game_loop().
    """
    while game_state == WELCOME:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                game_state = EXIT_GAME
                pygame.quit()
                quit()
            elif event.type == pygame.KEYDOWN:
                reset_game()  # Reset variables before starting
                game_loop()   # Start the main game
                game_state = PLAYING_GAME  # Ensure we switch to playing state

        # Draw welcome screen
        screen.fill(BLACK)
        title_text = font_large.render("CAR RACING GAME", True, WHITE)
        prompt_text = font_medium.render("Press any key to Start", True, GREEN)
        screen.blit(title_text, (screen_width // 2 - title_text.get_width() // 2, screen_height // 3))
        screen.blit(prompt_text, (screen_width // 2 - prompt_text.get_width() // 2, screen_height // 2))

        pygame.display.flip()
        clock.tick(60)

# Game States
WELCOME = 0
PLAYING_GAME = 1
GAME_OVER = 2
EXIT_GAME = 3

game_state = WELCOME

# Game loop
def game_loop():
    global game_state, exit_game, play_again_btn, currrent_lane, car_x, car_y, obstacles, score, road_y, level, level_timer

    while game_state != EXIT_GAME:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                game_state = EXIT_GAME

            # --- Playing game: arrow keys to move car ---
            elif game_state == PLAYING_GAME:
                if event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_LEFT and currrent_lane > 0:
                        currrent_lane -= 1
                        car_x = LANE_X_POSITIONS[currrent_lane]
                    elif event.key == pygame.K_RIGHT and currrent_lane < LANE_COUNTS - 1:
                        currrent_lane += 1
                        car_x = LANE_X_POSITIONS[currrent_lane]

            # --- Game over: click play again button ---
            elif game_state == GAME_OVER:
                if event.type == pygame.MOUSEBUTTONDOWN:
                    if play_again_btn.collidepoint(event.pos):
                        reset_game()
                        game_state = PLAYING_GAME

        # --- Update game state ---
        if game_state == PLAYING_GAME:
            # Level timer
            level_timer += clock.get_time()
            if level_timer >= level_interval:
                level += 1
                level_timer = 0

            # Obstacle speed
            obstacle_speed = 5 + level

            # Move road
            road_y += road_speed
            if road_y >= screen_height:
                road_y = 0

            # Generate obstacles
            if random.random() < 0.02:
                available_lanes = [i for i, lane_x in enumerate(LANE_X_POSITIONS)
                                   if all(not (obs[0] == lane_x and obs[1] < OBSTACLE_HEIGHT * 2) for obs in obstacles)]
                if available_lanes:
                    lane = random.choice(available_lanes)
                    obstacle_x = LANE_X_POSITIONS[lane]
                    obstacles.append([obstacle_x, -OBSTACLE_HEIGHT, get_random_obstacle()])

            # Move obstacles and check collisions
            for obstacle in obstacles:
                obstacle[1] += obstacle_speed
                if (car_x < obstacle[0] + OBSTACLE_WIDTH and car_x + CAR_WIDTH > obstacle[0] and
                    car_y < obstacle[1] + OBSTACLE_HEIGHT and car_y + CAR_HEIGHT > obstacle[1]):
                    game_state = GAME_OVER

            # Remove off-screen obstacles
            obstacles = [obs for obs in obstacles if obs[1] < screen_height]

            # Update score
            score += 1

            # Draw road
            screen.blit(road_image, (0, road_y - screen_height))
            screen.blit(road_image, (0, road_y))

            # Draw player car
            screen.blit(player_car, (car_x, car_y))

            # Draw obstacles
            for obstacle in obstacles:
                screen.blit(obstacle[2], (obstacle[0], obstacle[1]))

            # Draw score
            display_score(score, level)

        # Draw game over overlay
        if game_state == GAME_OVER:
            play_again_btn = display_game_over()

        pygame.display.flip()
        clock.tick(60)

    pygame.quit()
    quit()

def main():
    display_welcome_screen(game_state)  # Start here

if __name__ == "__main__":
    main()