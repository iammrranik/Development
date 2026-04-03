import pygame

# Creating Window
x = pygame.init()
gameWindow = pygame.display.set_mode((1024, 768))
title = pygame.display.set_caption("My First Game")

# Game specific variables
exit_game = False
gameOver = False

# Game Loop
while not exit_game:
    for event in pygame.event.get():
        # print(event)
        if event.type == pygame.QUIT:
            print("Game Closed")
            exit_game = True
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_RIGHT:
                print("Right Arrow Key Pressed")
            if event.key == pygame.K_LEFT:
                print("Left Arrow Key Pressed")
pygame.quit()
quit()