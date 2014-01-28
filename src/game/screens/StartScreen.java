package game.screens;

import game.SnakeGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * User: Zeejfps
 * Date: 1/25/14
 * Time: 9:40 PM
 */
public class StartScreen extends JPanel {

    public StartScreen(final SnakeGame game) {

        super(new BorderLayout());
        setBackground(Color.BLACK);

        final JPanel mainWrapper = new JPanel(new CardLayout());
        mainWrapper.setBackground(Color.BLACK);

        final JPanel menuWrapper = new JPanel(new BorderLayout());
        menuWrapper.setBackground(Color.BLACK);
        menuWrapper.setName("MenuWrapper");
        mainWrapper.add(menuWrapper, menuWrapper.getName());

        JPanel btnWrapper = new JPanel(new GridBagLayout());
        btnWrapper.setBackground(Color.BLACK);
        menuWrapper.add(btnWrapper, BorderLayout.CENTER);

        JLabel title = new JLabel("The Crazy Snake v1.0");
        title.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(60, 0, 0, 0),
            BorderFactory.createDashedBorder(null)));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 32));
        title.setForeground(Color.RED);
        menuWrapper.add(title, BorderLayout.NORTH);

        JLabel footer = new JLabel("By:Zeejfps");
        footer.setHorizontalAlignment(SwingConstants.RIGHT);
        footer.setForeground(Color.RED);
        footer.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 18));
        footer.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        menuWrapper.add(footer, BorderLayout.SOUTH);

        GridLayout gl = new GridLayout(3, 1);
        gl.setVgap(5);
        JPanel btnContainer = new JPanel(gl);
        btnContainer.setBackground(Color.BLACK);
        btnWrapper.add(btnContainer);

        JButton startBtn = createButton("Start");
        btnContainer.add(startBtn);

        JButton helpBtn = createButton("Help");
        btnContainer.add(helpBtn);

        JButton exitBtn = createButton("Exit");
        btnContainer.add(exitBtn);

        final JPanel helpMenuWrapper = new JPanel(new GridBagLayout());
        helpMenuWrapper.setBackground(Color.BLACK);
        helpMenuWrapper.setName("HelpMenuWrapper");
        mainWrapper.add(helpMenuWrapper, helpMenuWrapper.getName());

        final HelpMenu helpMenu = new HelpMenu();
        helpMenuWrapper.add(helpMenu);

        add(mainWrapper, BorderLayout.CENTER);

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.start();
            }
        });

        helpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)mainWrapper.getLayout()).show(mainWrapper, helpMenuWrapper.getName());
                helpMenu.requestFocusInWindow();
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        helpMenu.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    ((CardLayout) mainWrapper.getLayout()).show(mainWrapper, menuWrapper.getName());
                }

            }

        });

    }

    private JButton createButton(String text) {

        JButton btn = new JButton(text);
        btn.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 24));
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.RED);
        btn.setFocusable(false);

        final Border hoverBorder = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED, 3),
                BorderFactory.createEmptyBorder(0, 13, 0, 13));
        final Border normalBorder = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED),
                BorderFactory.createEmptyBorder(3, 15, 3, 15));

        btn.setBorder(normalBorder);
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JButton btn = (JButton)e.getSource();
                btn.setBorder(hoverBorder);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton btn = (JButton)e.getSource();
                btn.setBorder(normalBorder);
            }
        });
        return btn;
    }

    private class HelpMenu extends JPanel {

        private BufferedImage helpMenuImage;

        public HelpMenu() {
            setName("HelpMenu");
            setFocusable(true);
            try {
                helpMenuImage = ImageIO.read(SnakeGame.class.getResource("images/helpMenu.png"));
            } catch (IOException e) {
                System.err.println("Could not load helpMenu.png!");
                System.exit(1);
            }

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.drawImage(helpMenuImage, 0, 0, null);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(helpMenuImage.getWidth(), helpMenuImage.getHeight());
        }

    }

}
