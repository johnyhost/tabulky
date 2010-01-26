
public class GUI extends javax.swing.JFrame {

	public GUI() {
		initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {

		jToggleButton1 = new javax.swing.JToggleButton();
		jButton1 = new javax.swing.JButton();
		jMenuBar1 = new javax.swing.JMenuBar();
		S˙bor = new javax.swing.JMenu();
		jMenuItem1 = new javax.swing.JMenuItem();
		jMenu2 = new javax.swing.JMenu();
		jMenu3 = new javax.swing.JMenu();
		jMenu1 = new javax.swing.JMenu();
		jMenuItem2 = new javax.swing.JMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jToggleButton1.setText("jToggleButton1");

		jButton1.setText("jButton1");

		S˙bor.setText("S˙bor");

		jMenuItem1.setText("Nov˝ hr·Ë");
		S˙bor.add(jMenuItem1);

		jMenuBar1.add(S˙bor);

		jMenu2.setText("Upraviù");
		jMenuBar1.add(jMenu2);

		jMenu3.setText("N·stroje");
		jMenuBar1.add(jMenu3);

		jMenu1.setText("O aplika·cii");

		jMenuItem2.setText("O aplik·cii");
		jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem2ActionPerformed(evt);
			}
		});
		jMenu1.add(jMenuItem2);

		jMenuBar1.add(jMenu1);

		setJMenuBar(jMenuBar1);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400,
				Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 277,
				Short.MAX_VALUE));

		pack();
	}

	private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private javax.swing.JMenu S˙bor;
	private javax.swing.JButton jButton1;
	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JMenu jMenu3;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JMenuItem jMenuItem1;
	private javax.swing.JMenuItem jMenuItem2;
	private javax.swing.JToggleButton jToggleButton1;

}
