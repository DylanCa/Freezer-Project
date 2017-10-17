package projet.frigo;

import java.awt.Color;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

public class View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public final JLabel fieldTemperature;
	public final JLabel fieldHumidity;
	public final JLabel labelConsigne;
	public final JButton buttonConsignePlus;
	public final JButton buttonConsigneMinus;
	public final JButton btnAfficherGraph;
	public JLabel lblPointDeRose;
	public JLabel fieldTempRosee;
	
	private Controller controller;

	/**
	 * Create the frame.
	 */
	public View(Controller controller) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.controller = controller;
		setTitle("Mini-Frigo connecté");
		setBounds(100, 100, 424, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Temp\u00E9rature actuelle");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblHumidit = new JLabel("Humidit\u00E9");
		lblHumidit.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblConsigne = new JLabel("Temp\u00E9rature d\u00E9sir\u00E9e :");
		lblConsigne.setFont(new Font("Tahoma", Font.PLAIN, 18));

		fieldTemperature = new JLabel("0\u00B0C");
		fieldTemperature.setForeground(Color.RED);
		fieldTemperature.setFont(new Font("Tahoma", Font.PLAIN, 18));

		fieldHumidity = new JLabel("0%");
		fieldHumidity.setForeground(Color.BLUE);
		fieldHumidity.setFont(new Font("Tahoma", Font.PLAIN, 18));

		labelConsigne = new JLabel("0\u00B0C");
		labelConsigne.setForeground(Color.ORANGE);
		labelConsigne.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelConsigne.setText("17");

		buttonConsignePlus = new JButton("Make it HOT !");
		buttonConsignePlus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		

		buttonConsigneMinus = new JButton("Make it COLD !");
		buttonConsigneMinus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		btnAfficherGraph = new JButton("Afficher Graphiques en temps r\u00E9el");
		btnAfficherGraph.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel labelConsigneDegres = new JLabel("\u00B0C");
		labelConsigneDegres.setForeground(Color.ORANGE);
		labelConsigneDegres.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		lblPointDeRose = new JLabel("Point de Ros\u00E9e");
		lblPointDeRose.setHorizontalAlignment(SwingConstants.CENTER);
		lblPointDeRose.setFont(new Font("Tahoma", Font.PLAIN, 21));
		
		fieldTempRosee = new JLabel("0°C");
		fieldTempRosee.setHorizontalAlignment(SwingConstants.CENTER);
		fieldTempRosee.setForeground(Color.PINK);
		fieldTempRosee.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPointDeRose, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
						.addComponent(fieldTempRosee, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGap(77)
										.addComponent(lblHumidit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGap(50))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
											.addComponent(lblConsigne, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
											.addComponent(buttonConsigneMinus, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(58)
												.addComponent(labelConsigne)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(labelConsigneDegres, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
												.addGap(48))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(18)
												.addComponent(buttonConsignePlus, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))))
									.addComponent(btnAfficherGraph, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(73)
								.addComponent(fieldTemperature)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(fieldHumidity)
								.addGap(69))))
					.addGap(24))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHumidit, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(fieldHumidity, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(fieldTemperature, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(btnAfficherGraph, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConsigne, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelConsigneDegres, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelConsigne, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(buttonConsigneMinus)
						.addComponent(buttonConsignePlus, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(lblPointDeRose)
					.addGap(18)
					.addComponent(fieldTempRosee)
					.addGap(17))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
