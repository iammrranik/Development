# Entities/Car.py
# Abstract base class (interface-like) for moving car objects in the game.
from abc import ABC, abstractmethod
import pygame
import Db.FileLoader as FileLoader
import random
from Settings import OBSTACLE_WIDTH, OBSTACLE_HEIGHT, RED
from Db.Paths import cars_image_path

class Car(ABC):
    def __init__(self, x: int, y: int, width: int, height: int, image: pygame.Surface = None):
        self.x = x
        self.y = y
        self.width = width
        self.height = height
        if image is not None:
            self.image = pygame.transform.scale(image, (self.width, self.height))
        else:
            self.image = pygame.Surface((self.width, self.height), pygame.SRCALPHA)
            self.image.fill((RED, 255))

    @abstractmethod
    def update(self, *args, **kwargs):
        """Update internal state (position, physics, etc.)."""
        raise NotImplementedError

    @abstractmethod
    def draw(self, surface: pygame.Surface):
        """Render the car on a surface."""
        raise NotImplementedError

    def get_rect(self) -> pygame.Rect:
        return pygame.Rect(self.x, self.y, self.width, self.height)

    @staticmethod
    def get_random_car():
        """
        Get a random car texture from assets path.
        This uses configured paths and avoids hardcoded string paths in logic.
        """
        car_models = FileLoader.load_multiple_images(cars_image_path)
        if not car_models:
            # default fallback if no assets found
            placeholder = pygame.Surface((OBSTACLE_WIDTH, OBSTACLE_HEIGHT), pygame.SRCALPHA)
            placeholder.fill((RED, 255))
            return placeholder

        car = random.choice(list(car_models.values()))
        return pygame.transform.scale(car, (OBSTACLE_WIDTH, OBSTACLE_HEIGHT))
