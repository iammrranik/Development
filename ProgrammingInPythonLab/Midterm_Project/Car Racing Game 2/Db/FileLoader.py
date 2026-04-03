import pygame
import os
from Settings import TRANSPARENT_APPHA_NONE

class FileLoader:
    # Manager/Service class for assets.
    
    @staticmethod
    def load_single_icon(path):
        if os.path.exists(path):
            return pygame.image.load(path)
        return None
    
    @staticmethod  # This allows you to call the method without creating an object
    def load_single_image(path, alpha=TRANSPARENT_APPHA_NONE):
        if os.path.exists(path):
            img = pygame.image.load(path).convert_alpha()
            img.set_alpha(alpha)
            return img
        return None

    @staticmethod
    def load_multiple_images(directory):
        images = {}
        if os.path.exists(directory):
            for file_name in os.listdir(directory):
                if file_name.endswith(".png"):
                    path = os.path.join(directory, file_name)
                    name = file_name.replace(".png", "")
                    # Call the other static method using the Class Name
                    images[name] = FileLoader.load_single_image(path)
        return images
    
    @staticmethod
    def load_single_sound(path)-> pygame.mixer.music.load:
        if os.path.exists(path):
            return pygame.mixer.music.load(path)
        return None