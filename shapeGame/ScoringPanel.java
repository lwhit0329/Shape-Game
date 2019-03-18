package uncp.csc1900.projects.shapeGame;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class to hold the displaying score informaiton
 * 
 * @author Lewis A. Whitley
 * @version 11/25/2013
 */

public class ScoringPanel extends JPanel {

	private int score;
	private JLabel displayScore;

	/**
	 * Purpose to create a score to display
	 * 
	 */

	public ScoringPanel() {
		score = 0;
		displayScore = new JLabel("0");
		JLabel scoreText = new JLabel("Score");

		FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 10, 0);
		setLayout(layout);
		add(scoreText);
		add(displayScore);
	}

	/**
	 * Purpose to add points to score
	 * 
	 * @param addScore
	 *            the amount added to score
	 */

	public void incScore(int addScore) {
		score += addScore;
		displayScore.setText("" + score);

	}

	/**
	 * Purpose to reset the score to zero
	 */

	public void resetScore() {
		score = 0;
		displayScore.setText("0");
	}

	/**
	 * Purpose is to get the value of score
	 * 
	 * @return return the int value of score
	 */

	public int getScore() {
		return score;
	}

}
