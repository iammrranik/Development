import random
import pygame
from Entities.Car import Car
from Settings import LANE_X_POSITIONS, OBSTACLE_WIDTH, OBSTACLE_HEIGHT, screen_height

class CarObstacle(Car):
    # Obstacle car that moves downward to be avoided.
    def __init__(self, image: pygame.Surface, lane_x: int = None, speed: float = 5.0):
        lane_x = lane_x if lane_x is not None else random.choice(LANE_X_POSITIONS)
        start_y = -OBSTACLE_HEIGHT
        rotated_image = pygame.transform.rotate(image, 180)  # Rotate to face downward
        super().__init__(lane_x, start_y, OBSTACLE_WIDTH, OBSTACLE_HEIGHT, rotated_image)
        self.speed = speed

    def update(self, delta_ms: float = 1.0):
        self.y += (self.speed * (delta_ms / 16.67))  # Normalize to 60FPS baseline

    def draw(self, surface: pygame.Surface):
        surface.blit(self.image, (self.x, self.y))

    def is_off_screen(self) -> bool:
        return self.y > screen_height
    