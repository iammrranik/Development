import random
import pygame
from Entities.Car import Car
from Settings import LANE_X_POSITIONS, LANE_COUNTS, CAR_WIDTH, CAR_HEIGHT, CAR_BOTTOM_OFFSET, screen_height

class CarPlayer(Car):
    # User-controlled car.
    def __init__(self, image: pygame.Surface):
        self.lane_index = LANE_COUNTS // 2
        x = LANE_X_POSITIONS[self.lane_index]
        y = screen_height - CAR_HEIGHT - CAR_BOTTOM_OFFSET
        super().__init__(x, y, CAR_WIDTH, CAR_HEIGHT, image)

    def move(self, direction: str):
        if direction == "LEFT" and self.lane_index > 0:
            self.lane_index -= 1
        elif direction == "RIGHT" and self.lane_index < LANE_COUNTS - 1:
            self.lane_index += 1

        self.x = LANE_X_POSITIONS[self.lane_index]

    def update(self, *args, **kwargs):
        pass

    def draw(self, surface: pygame.Surface):
        surface.blit(self.image, (self.x, self.y))
