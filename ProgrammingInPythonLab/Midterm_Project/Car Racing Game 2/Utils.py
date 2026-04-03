import csv
import os.path
import pygame
import random

import Settings
from Db.Paths import hiscore_path


def get_random_car_image(car_models: dict) -> pygame.Surface:
    """Returns a scaled random car sprite from the loaded model dictionary."""
    if not car_models:
        fallback = pygame.Surface((Settings.CAR_WIDTH, Settings.CAR_HEIGHT), pygame.SRCALPHA)
        fallback.fill((Settings.RED, 255))
        return fallback

    img = random.choice(list(car_models.values()))
    size = (Settings.OBSTACLE_WIDTH, Settings.OBSTACLE_HEIGHT)
    return pygame.transform.scale(img, size)


def get_high_scores(limit=5):
    """Read sorted high scores (desc) from CSV file as (score, level, high_score)."""
    if not os.path.exists(hiscore_path):
        return []

    entries = []
    try:
        with open(hiscore_path, mode="r", newline="", encoding="utf-8") as f:
            reader = csv.reader(f)
            for row in reader:
                if len(row) < 3:
                    continue
                try:
                    score = int(row[0])
                    level = int(row[1])
                    high_score = int(row[2])
                except ValueError:
                    continue
                entries.append((score, level, high_score))
    except Exception:
        return []

    # sort by first element (score) in descending order
    entries.sort(key=sort_by_score_desc, reverse=True)
    return entries[:limit]


def save_high_scores(entries):
    """Save sorted high score entries to CSV file (max 5 rows)."""
    # sort by score descending, keep top 5
    sorted_entries = sorted(entries, key=sort_by_score_desc, reverse=True)[:5]
    os.makedirs(os.path.dirname(hiscore_path), exist_ok=True)

    with open(hiscore_path, mode="w", newline="", encoding="utf-8") as f:
        writer = csv.writer(f)
        for score, level, high_score in sorted_entries:
            writer.writerow([score, level, high_score])


def update_high_scores(score: int, level: int, high_score: int, limit=5):
    existing = get_high_scores(limit)
    existing.append((score, level, high_score))
    existing.sort(key=sort_by_score_desc, reverse=True)
    top = existing[:limit]
    save_high_scores(top)
    return top


def sort_by_score_desc(item):
    """Return the score for comparing high score entries."""
    # item is (score, level, top_score)
    return item[0]


def display_score(surface: pygame.Surface, score: int, level: int, top_score: int):
    """Render score, level and high score in the game UI."""
    score_text = Settings.font_small.render(f"Score: {score}", True, Settings.WHITE)
    level_text = Settings.font_small.render(f"Level: {level}", True, Settings.WHITE)
    high_score_text = Settings.font_small.render(f"High Score: {top_score}", True, Settings.WHITE)
    surface.blit(score_text, (10, 10))
    surface.blit(level_text, (10, 30))
    surface.blit(high_score_text, (10, 50))

