import sys
import os
import random

# Get the parent directory of the current script (Connect4CLI.py)
current_dir = os.path.dirname(os.path.abspath(__file__))
parent_dir = os.path.dirname(current_dir)

# Add the parent directory to the Python path
sys.path.append(parent_dir + "/Connect4") # for mac

from Connect4 import GameState
import errors
from Connect4 import Connect4

def draw_board(board):
    """
    Draws the board to the console.
    :param board: The game board to draw.
    """
    for row in board:
        for index, cell in enumerate(row):
            if (index == 0):
                print("|_", end=' ')
            else:
                print("_|_", end=' ')
            print(cell, end=' ')
        print("_|")

def getInput(game):
    if game.current_game_state() is GameState.TURN_PLAYER_1:
        column = input()
    else:
        column = random.randint(0, 7)
    return column

def play(game):
    """
    Plays the game.
    :param game: The game object.
    """
    while True:
        # ask player for input
        print("Enter a column number to drop a checker into: ")
        
                
        column = getInput(game)
        
        try:
            state = game.drop_checker(column)
            print(state)

            if state == GameState.WIN_PLAYER_1 or state == GameState.WIN_PLAYER_2 or state == GameState.TIE:
                break
        except (errors.ColumnOutOfRangeError, errors.ColumnFullError, errors.GameOverError, TypeError) as error:
            print(error)
            continue

        draw_board(game.board())
        continue

def printInitialGame(game):
    print("Player 1 is Human")
    print("Player 2 is Computer")
    print(game.current_game_state())
    draw_board(game.board())

def printFinalBoard(game):
    print("Final board:")
    draw_board(game.board())
    print("Game over.")
    print("===============")

def resume():
    print("\n")
    print("Press 'y' to start a new game or press any other key to exit")
    response = input()
    return response == 'y'
    
def main():
    print("Welcome to Connect 4!")
    print("---------------------")

    while True:

        # initialize game
        game = Connect4()

        # output initial game promp
        printInitialGame(game)

        # start playing
        play(game)

        # output result
        printFinalBoard(game)
        
        # check resume
        if not resume():
            break

if __name__ == '__main__':
    main()