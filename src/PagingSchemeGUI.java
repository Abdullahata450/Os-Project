import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PagingSchemeGUI extends JFrame {

    private JTextField processSizeField;
    private JTextField frameSizeField;
    private JLabel maxPageTableEntrySizeLabel;
    private JLabel pageTableSizeLabel;
    private JLabel totalFramesLabel;


    public PagingSchemeGUI() {
        createUI();
    }

    private void createUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Enter the process size (in MB):"));
        processSizeField = new JTextField(10);
        inputPanel.add(processSizeField);

        inputPanel.add(new JLabel("Enter the frame size (in KB):"));
        frameSizeField = new JTextField(10);
        inputPanel.add(frameSizeField);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this::calculate);
        inputPanel.add(new JLabel()); // Empty label for layout purposes
        inputPanel.add(calculateButton);

        // Result panel
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(2, 1, 5, 5));
        resultPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        maxPageTableEntrySizeLabel = new JLabel("Maximum page table entry size: ");
        pageTableSizeLabel = new JLabel("Page table size: ");
        totalFramesLabel = new JLabel("Total number of frames: ");
        resultPanel.add(totalFramesLabel);


        // Set font style for result labels
        Font resultFont = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        maxPageTableEntrySizeLabel.setFont(resultFont);
        pageTableSizeLabel.setFont(resultFont);
        totalFramesLabel.setFont(resultFont);

        resultPanel.add(maxPageTableEntrySizeLabel);
        resultPanel.add(pageTableSizeLabel);

        // Add components to the main frame
        add(inputPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private void calculate(ActionEvent e) {
        try {
            int processSizeMB = Integer.parseInt(processSizeField.getText());
            int frameSizeKB = Integer.parseInt(frameSizeField.getText());

            if (frameSizeKB <= 0 || processSizeMB <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter valid positive values for process size and frame size.");
                return;
            }

            int numberOfPages = (processSizeMB * 1024) / frameSizeKB;
            int maxPageTableEntrySize = calculateMaxPageTableEntrySize(numberOfPages);
            int pageTableSize = numberOfPages * maxPageTableEntrySize;

            maxPageTableEntrySizeLabel.setText("Maximum page table entry size: " + maxPageTableEntrySize + " bytes.");
            pageTableSizeLabel.setText("Page table size: " + pageTableSize + " bytes.");

            // Calculate total number of frames
            int totalFrames = (processSizeMB * 1024) / frameSizeKB;
            totalFramesLabel.setText("Total number of frames: " + totalFrames);

            // Open the memory visualization in a new frame
            MemoryPanelFrame memoryFrame = new MemoryPanelFrame(numberOfPages);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for process size and frame size.");
        }
    }

    private int calculateMaxPageTableEntrySize(int numberOfPages) {
        return Integer.SIZE / 8; // Assuming 32-bit address space
    }

    class MemoryPanelFrame extends JFrame {

        private MemoryPanel memoryPanel;
        private JScrollPane scrollPane;

        public MemoryPanelFrame(int numberOfFrames) {
            memoryPanel = new MemoryPanel();
            memoryPanel.setNumberOfFrames(numberOfFrames);

            // Creating a scroll pane that contains the memory panel
            scrollPane = new JScrollPane(memoryPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            add(scrollPane);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(500, 400); // Adjusted for better visibility with the scroll pane
            setLocationRelativeTo(null);
            setTitle("Memory Panel");
            setVisible(true);
        }

        class MemoryPanel extends JPanel {
            private int numberOfFrames = 0;

            public void setNumberOfFrames(int numberOfFrames) {
                this.numberOfFrames = numberOfFrames;
                int preferredHeight = Math.max(200, 20 * numberOfFrames);
                setPreferredSize(new Dimension(400, preferredHeight));
                revalidate(); // To update the scroll pane
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (numberOfFrames > 0) {
                    int width = getWidth();
                    int frameHeight = 20; // Fixed height for each frame
                    g.setColor(Color.BLUE);

                    for (int i = 0; i < numberOfFrames; i++) {
                        int y = i * frameHeight;
                        g.drawRect(0, y, width - 1, frameHeight - 1); // Adjusting width and height to fit inside panel
                        g.drawString("Frame " + (i + 1) + " - Frame Size: " + frameSizeField.getText() + " KB", 5, y + frameHeight / 2 + 5);
                        // Centering text in each frame
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PagingSchemeGUI());
    }
}
