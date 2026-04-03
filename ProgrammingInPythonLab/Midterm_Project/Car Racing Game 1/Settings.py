# This file contains global settings and constants for the game
import pygame.font

# Game Colors
WHITE = (255, 255, 255)
RED = (255, 0, 0)
GREEN = (0, 255, 0)
BLUE = (0, 0, 255)
BLACK = (0, 0, 0)
ROAD_BACKGROUND = (50, 50, 50)
OVERLAY_SEMI_TRANSPARENT = (0, 0, 0, 150)

# Window Settings
GAME_TITLE = "Car Racing Game"
FPS = 60
screen_width = 840
screen_height = 650

# Game Pacing
SPAWN_OBSTACLE_EVERY_MS = 1500
LEVEL_INTERVAL_MS = 5000

# Initial speed settings
INITIAL_OBSTACLE_SPEED = 5
OBSTACLE_SPEED_INCREMENT = 1
PLAYER_SPEED = 5
ROAD_SPEED = 5

# Game States
WELCOME = 1
PLAYING_GAME = 2
GAME_OVER = 3
EXIT_GAME = 4

# Fonts and UI
font_small = pygame.font.Font(None, 24)
font_medium = pygame.font.Font(None, 36)
font_large = pygame.font.Font(None, 72)

# Car Properties
CAR_WIDTH = 60
CAR_HEIGHT = 100
CAR_BOTTOM_OFFSET = 20
OBSTACLE_WIDTH = 60
OBSTACLE_HEIGHT = 100

# Lane Settings
LANE_X_POSITIONS = [200, 324, 454, 581]
LANE_COUNTS = len(LANE_X_POSITIONS)

# Gameplay Constants
MAX_OBSTACLES = 8
PLAY_AGAIN_BTN_SIZE = (220, 50)
PLAY_AGAIN_BTN_OFFSET_Y = 2/3
ROAD_START_Y = 0
