import pygame
import random

from Settings import CAR_WIDTH, CAR_HEIGHT, OBSTACLE_WIDTH, OBSTACLE_HEIGHT, font_small, WHITE, RED


def get_random_car_image(car_models: dict) -> pygame.Surface:
    """Returns a scaled random car sprite from the loaded model dictionary."""
    if not car_models:
        fallback = pygame.Surface((CAR_WIDTH, CAR_HEIGHT), pygame.SRCALPHA)
        fallback.fill((*RED, 255))
        return fallback

    img = random.choice(list(car_models.values()))
    size = (OBSTACLE_WIDTH, OBSTACLE_HEIGHT)
    return pygame.transform.scale(img, size)


def display_score(surface: pygame.Surface, score: int, level: int):
    score_text = font_small.render(f"Score: {score}", True, WHITE)
    level_text = font_small.render(f"Level: {level}", True, WHITE)
    surface.blit(score_text, (10, 10))
    surface.blit(level_text, (10, 30))






