import pygetwindow as mw
from pynput import mouse, keyboard
import time
import sys

# Initialize the keyboard controller to simulate key presses
kb_controller = keyboard.Controller()

def create_tab_and_minimize():
    # 1. Get the window currently in focus
    active_window = mw.getActiveWindow()
    
    # 2. Check if 'Brave' is in the title of the active window
    if active_window and 'Brave' in active_window.title:
        # 3. Only act if it is visible
        if not active_window.isMinimized:
            
            # # Press Ctrl + T to create a new tab
            # with kb_controller.pressed(keyboard.Key.ctrl):
            #     kb_controller.press('t')
            #     kb_controller.release('t')
            
            # Small delay to let the browser process the new tab before minimizing
            time.sleep(0.1) 
            
            active_window.minimize()
            print(f"\n[Action] New tab created & Brave Minimized!")
            print("Type 'c' and press Enter to exit: ", end='', flush=True)

def on_click(x, y, button, pressed):
    # Triggered by clicking the Scroll Wheel (Middle Button)
    if button == mouse.Button.middle and pressed:
        create_tab_and_minimize()

# Start the mouse listener in the background
listener = mouse.Listener(on_click=on_click)
listener.start()

print("--- Brave 'Tab & Hide' Active ---")
print("Rules: Click Scroll Wheel to: (1) New Tab + (2) Minimize.")

try:
    while True:
        user_input = input("Type 'c' and press Enter to exit: ").lower().strip()
        if user_input == 'c':
            print("Shutting down...")
            break
finally:
    listener.stop()





