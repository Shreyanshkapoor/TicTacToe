import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class TicTacToe implements ActionListener {

    private JFrame frame;
    private JPanel titlePanel, buttonPanel;
    private JLabel textField;
    private JButton[] buttons;
    private JButton restartButton; 
    private boolean player1Turn;
    private boolean gameEnded;

    TicTacToe() {
        frame = new JFrame();
        titlePanel = new JPanel();
        buttonPanel = new JPanel();
        textField = new JLabel("Tic-Tac-Toe");
        buttons = new JButton[9];
        restartButton = new JButton("Restart");
        player1Turn = true;
        gameEnded = false;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textField.setBackground(new Color(25, 25, 25));
        textField.setForeground(new Color(25, 255, 0));
        textField.setFont(new Font("Ink Free", Font.BOLD, 75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 100);

        buttonPanel.setLayout(new GridLayout(3, 3));
        buttonPanel.setBackground(new Color(150, 150, 150));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        restartButton.setFont(new Font("Arial", Font.PLAIN, 20));
        restartButton.setFocusable(false);
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });

        titlePanel.add(textField, BorderLayout.CENTER);
        titlePanel.add(restartButton, BorderLayout.EAST);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(buttonPanel);

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameEnded)
            return;

        JButton clickedButton = (JButton) e.getSource();

        if (clickedButton.getText().equals("")) {
            if (player1Turn) {
                clickedButton.setForeground(new Color(255, 0, 0));
                clickedButton.setText("X");
                player1Turn = false;
                textField.setText("O's turn");
            } else {
                clickedButton.setForeground(new Color(0, 0, 255));
                clickedButton.setText("O");
                player1Turn = true;
                textField.setText("X's turn");
            }
            check();
        }
    }

    private void firstTurn() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        player1Turn = true;
        textField.setText("X's turn");
    }

    private void check() {
        String[][] board = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = buttons[i * 3 + j].getText();
            }
        }

        for (int i = 0; i < 3; i++) {
        
            if (board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2]) && !board[i][0].equals("")) {
                gameOver(board[i][0]);
                return;
            }
          
            if (board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i]) && !board[0][i].equals("")) {
                gameOver(board[0][i]);
                return;
            }
        }
     
        if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && !board[0][0].equals("")) {
            gameOver(board[0][0]);
            return;
        }
        if (board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]) && !board[0][2].equals("")) {
            gameOver(board[0][2]);
            return;
        }

       
        boolean draw = true;
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().equals("")) {
                draw = false;
                break;
            }
        }
        if (draw) {
            gameOver("draw");
        }
    }

    private void gameOver(String winner) {
        gameEnded = true;

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }

        if (winner.equals("X")) {
            textField.setText("X wins");
        } else if (winner.equals("O")) {
            textField.setText("O wins");
        } else {
            textField.setText("It's a draw");
        }
    }

    private void restartGame() {
        
        gameEnded = false;
        player1Turn = true;

        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
            buttons[i].setForeground(Color.BLACK); 
        }

 
        textField.setText("Tic-Tac-Toe");
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
