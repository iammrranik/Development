import os
import pygame
import random

pygame.mixer.init()
pygame.init()

# Colors
white = (255, 255, 255)
red = (255, 0, 0)
green = (0, 255, 0)
blue = (0, 0, 255)
black = (0, 0, 0)

# Game Screen Variables
screen_width = 900
screen_height = 600



# Creating Window
game_window = pygame.display.set_mode((screen_width, screen_height))
pygame.display.set_caption("SnakesWithAnik")
# pygame.display.set_icon(pygame.image.load("snake.png"))
# pygame.display.update()
clock = pygame.time.Clock()
font = pygame.font.SysFont(None, 55)

# Background Image
bgimg = pygame.image.load("images.jfif")
bgimg = pygame.transform.scale(bgimg, (screen_width, screen_height)).convert_alpha()

def text_screen(text, color, x, y):
    screen_text = font.render(text, True, color)
    game_window.blit(screen_text, [x, y])

def plot_snake(game_window, color, snake_list, snake_width, snake_height):
    # print(snake_list)
    for snake_x, snake_y in snake_list:
        pygame.draw.rect(game_window, color, [snake_x, snake_y, snake_width, snake_height])

def welcome():
    pygame.mixer.music.load("charmer.mp3")
    pygame.mixer.music.play()
    
    exit_game = False
    
    while not exit_game:
        game_window.fill((233, 220, 229))
        text_screen("Welcome To SnakesWithAnik", black, 100, 250)
        text_screen("Press Space To Play", black, 100, 350)
        
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                exit_game = True
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_SPACE:
                    game_loop()
        
        pygame.display.update()
        clock.tick(60)

# Game Loop
def game_loop():
    # Game Specific Variables
    exit_game = False
    game_over = False

    fps = 60

    snake_x = 45
    snake_y = 55
    snake_width = 30
    snake_height = 30
    snake_length_increment = 5

    velocity_x = 0
    velocity_y = 0
    velocity_init = 5

    food_x = random.randint(50, int(screen_width/2))
    food_y = random.randint(50, int(screen_height/2))
    food_width = 20
    food_height = 20

    score_x = 5
    score_y = 5
    score = 0
    score_increment = 10
    hiscore = 0
    
    if not os.path.exists("highscore.txt"):
        with open("highscore.txt", "w") as f:
            f.write("0")
    
    with open("highscore.txt", "r") as f:
        content = f.read().strip()
        hiscore = int(content) if content != "" else 0
    
    # Snake Length Logic
    snake_list = []
    snake_length = 1
    
    while not exit_game:
        if game_over:
            game_window.fill(white)
            text_screen("Game Over! Press Enter To Continue", red, 100, 250)
            text_screen("Your Score: " + str(score), blue, 100, 350)
            text_screen("High Score: " + str(hiscore), green, 100, 450)
            
            if score > hiscore:
                with open("highscore.txt", "w") as f:
                    f.write(str(score))
                hiscore = score
            
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    exit_game = True
                if event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_RETURN:
                        exit_game = False
                        game_over = False
                        game_loop()
                    if event.key == pygame.K_SPACE:
                        exit_game=False
                        game_over = False
                        welcome()
            
        else:
            for event in pygame.event.get():
                # print(event)
                if event.type == pygame.QUIT:
                    exit_game = True
                if event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_RIGHT:
                        velocity_x = velocity_init
                        velocity_y = 0
                    if event.key == pygame.K_LEFT:
                        velocity_x = -velocity_init
                        velocity_y = 0
                    if event.key == pygame.K_UP:
                        velocity_x = 0
                        velocity_y = -velocity_init
                    if event.key == pygame.K_DOWN:
                        velocity_x = 0
                        velocity_y = velocity_init
                    if event.key == pygame.K_RETURN and game_over:
                        game_loop()
                        game_over = False
                    if event.key == pygame.K_q:
                        score += score_increment
                        snake_length += snake_length_increment
                    
            
            snake_x += velocity_x
            snake_y += velocity_y
            
            if(abs(snake_x - food_x) < 30 and abs(snake_y - food_y) < 30):
                pygame.mixer.music.load("beep.ogg")
                pygame.mixer.music.play()
                score += score_increment
                snake_length += snake_length_increment
                # print("Score: ", score * 10)
                food_x = random.randint(50, int(screen_width/2))
                food_y = random.randint(50, int(screen_height/2))
            
            game_window.fill(white)
            game_window.blit(bgimg, (0, 0))
            text_screen("Score: " + str(score), blue, score_x, score_y)
            text_screen("High Score: " + str(hiscore), green, score_x + 250, score_y)
            
            head = []
            head.append(snake_x)
            head.append(snake_y)
            snake_list.append(head)
            
            if(len(snake_list) > snake_length):
                del snake_list[0]
            
            if snake_x < 0 or snake_x > screen_width or snake_y < 0 or snake_y > screen_height:
                pygame.mixer.music.load("explosion.wav")
                pygame.mixer.music.play()
                game_over = True
                text_screen("Game Over! Press Enter To Continue", red, 100, 250)
                pygame.display.update()
            elif head in snake_list[:-1]:
                pygame.mixer.music.load("explosion.wav")
                pygame.mixer.music.play()
                game_over = True
                text_screen("Game Over! Press Enter To Continue", red, 100, 250)
                pygame.display.update()
            
            plot_snake(game_window, black, snake_list, snake_width, snake_height)
            pygame.draw.rect(game_window, red, [food_x, food_y, food_width, food_height])
        
        pygame.display.update()
        clock.tick(fps)
        
    pygame.quit()
    quit()

welcome()