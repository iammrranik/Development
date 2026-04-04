import pygetwindow as mw
from pynput import mouse, keyboard
import time
import threading
import tkinter as tk
from tkinter import messagebox
import pystray
from PIL import Image, ImageDraw
import sys
import os

class BraveHiderApp:
    def __init__(self, root):
        self.root = root
        self.root.title("Brave Stealth")
        self.root.geometry("300x200")
        
        self.kb_controller = keyboard.Controller()
        self.mouse_listener = None
        self.is_running = False
        self.icon = None

        # --- UI ---
        self.status_label = tk.Label(root, text="Status: IDLE", fg="gray", font=("Arial", 10, "bold"))
        self.status_label.pack(pady=15)

        self.toggle_btn = tk.Button(root, text="START LISTENER", command=self.toggle_service, width=15, height=2)
        self.toggle_btn.pack(pady=5)

        self.hide_btn = tk.Button(root, text="Minimize to Tray", command=self.hide_to_tray)
        self.hide_btn.pack(pady=5)

        self.exit_btn = tk.Button(root, text="Exit Application", command=self.quit_app, fg="red")
        self.exit_btn.pack(pady=10)

        # Handle window 'X' button
        self.root.protocol('WM_DELETE_WINDOW', self.hide_to_tray)

    def create_tab_and_minimize(self):
        active_window = mw.getActiveWindow()
        if active_window and 'Brave' in active_window.title:
            if not active_window.isMinimized:
                time.sleep(0.1) 
                active_window.minimize()

    def on_click(self, x, y, button, pressed):
        if button == mouse.Button.middle and pressed:
            self.create_tab_and_minimize()

    def toggle_service(self):
        if not self.is_running:
            self.is_running = True
            self.mouse_listener = mouse.Listener(on_click=self.on_click)
            self.mouse_listener.start()
            self.status_label.config(text="Status: ACTIVE", fg="green")
            self.toggle_btn.config(text="STOP LISTENER", bg="#90ee90")
        else:
            self.stop_listener()

    def stop_listener(self):
        self.is_running = False
        if self.mouse_listener:
            self.mouse_listener.stop()
        self.status_label.config(text="Status: IDLE", fg="gray")
        self.toggle_btn.config(text="START LISTENER", bg="#f0f0f0")

    def create_icon_image(self):
        # Create a simple 64x64 blue square icon
        image = Image.new('RGB', (64, 64), (255, 255, 255))
        dc = ImageDraw.Draw(image)
        dc.rectangle([16, 16, 48, 48], fill=(0, 120, 215))
        return image

    def hide_to_tray(self):
        self.root.withdraw() # Hide window
        
        # Define the tray menu
        menu = (pystray.MenuItem('Show Window', self.show_window), 
                pystray.MenuItem('Exit', self.quit_app))
        
        self.icon = pystray.Icon("BraveHider", self.create_icon_image(), "Brave Stealth", menu)
        
        # Important: Run the icon in a separate thread so it doesn't freeze
        threading.Thread(target=self.icon.run, daemon=True).start()

    def show_window(self, icon=None):
        if self.icon:
            self.icon.stop()
        self.root.after(0, self.root.deiconify)

    def quit_app(self, icon=None):
        if self.icon:
            self.icon.stop()
        self.stop_listener()
        self.root.quit()
        os._exit(0)

if __name__ == "__main__":
    root = tk.Tk()
    app = BraveHiderApp(root)
    root.mainloop()
