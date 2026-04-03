import pygame
import os

class FileLoader:
    '''Manager/Service class for assets.'''
    
    @staticmethod  # This allows you to call the method without creating an object
    def load_single_image(path):
        if os.path.exists(path):
            # Ensure pygame.display.set_mode() was called in main before this!
            return pygame.image.load(path).convert_alpha()
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