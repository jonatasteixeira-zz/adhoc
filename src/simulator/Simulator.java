package simulator;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import language.Language;
import area.Area;


public class Simulator extends JFrame{

	private JMenuBar menuBar;
//	private Statusbar statusBar;
	private Area area;
	
	
	
	public Simulator() {
		this.initWindow();
	}
	
	public Simulator(int hosts) {
		this.initWindow();
	}
	
	private void initWindow() {

		
        this.setTitle(Language.simulatorName);
        
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(580,580));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(580,580));

		

        this.initMenuBar();
        this.initDisplay();

//      this.initStatusBar();
              
        this.setVisible(true);

	}

	private void initMenuBar() {
		this.menuBar = new JMenuBar();		
		
		JMenu menuFile = new JMenu(Language.menuFile);
		JMenu menuEdit = new JMenu(Language.menuEdit);
		JMenu menuView = new JMenu(Language.menuView);
		JMenu menuHelp = new JMenu(Language.menuHelp);
		
		JMenuItem  menuFileNew = new JMenuItem (Language.menuFileNew);
		JMenuItem  menuFileOpen = new JMenuItem (Language.menuFileOpen);
		JMenuItem  menuFileSave = new JMenuItem (Language.menuFileSave);
		JMenuItem  menuFileSaveAs = new JMenuItem (Language.menuFileSaveAs);
		JMenuItem  menuFileClose = new JMenuItem (Language.menuFileClose);
		JMenuItem  menuFileQuit = new JMenuItem (Language.menuFileQuit);
		JMenuItem  menuEditUndo = new JMenuItem (Language.menuEditUndo);
		JMenuItem  menuEditRedo = new JMenuItem (Language.menuEditRedo);
		JMenuItem  menuEditCut = new JMenuItem (Language.menuEditCut);
		JMenuItem  menuEditCopy = new JMenuItem (Language.menuEditCopy);
		JMenuItem  menuEditPaste = new JMenuItem (Language.menuEditPaste);
		JMenuItem  menuEditAdd = new JMenuItem (Language.menuEditAdd);
		JMenuItem  menuEditRemove = new JMenuItem (Language.menuEditRemove);
		JMenuItem  menuEditStart = new JMenuItem (Language.menuEditStart);
		JMenuItem  menuEditStop = new JMenuItem (Language.menuEditStop);
		JCheckBoxMenuItem  menuViewToolBar = new JCheckBoxMenuItem (Language.menuViewToolBar);
		JCheckBoxMenuItem  menuViewStatusBar = new JCheckBoxMenuItem (Language.menuViewStatusBar);
		JCheckBoxMenuItem  menuViewFullScreen = new JCheckBoxMenuItem (Language.menuViewFullScreen);
		JMenuItem  menuHelpAbout = new JMenuItem (Language.menuHelpAbout);

		
		
		menuFileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menuFileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		menuFileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		menuFileSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		menuFileClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
		menuFileQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		menuEditUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		menuEditRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
		menuEditCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		menuEditCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		menuEditPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		menuEditAdd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, ActionEvent.CTRL_MASK));
		menuEditRemove.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, ActionEvent.CTRL_MASK));
		menuEditStart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, ActionEvent.CTRL_MASK));
		menuEditStop.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, ActionEvent.CTRL_MASK));
		menuViewToolBar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9, ActionEvent.CTRL_MASK));
		menuViewStatusBar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10, ActionEvent.CTRL_MASK));
		menuViewFullScreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, ActionEvent.CTRL_MASK));
		menuHelpAbout.setAccelerator(KeyStroke.getKeyStroke("F1"));

		
		/*
		menuFileNew.connect(new JMenuItem.Activate() {
			public void onActivate(JMenuItem JMenuItem) { menuFileNew(); }
		});
		menuFileOpen.connect(new JMenuItem.Activate() {
			public void onActivate(JMenuItem JMenuItem) { menuFileOpen(); }
		});
		menuFileSave.connect(new JMenuItem.Activate() {
			public void onActivate(JMenuItem JMenuItem) { menuFileSave(); }
		});
		menuFileSaveAs.connect(new JMenuItem.Activate() {
			public void onActivate(JMenuItem JMenuItem) { menuFileSaveAs(); }
		});
		menuFileClose.connect(new JMenuItem.Activate() {
			public void onActivate(JMenuItem JMenuItem) { menuFileClose(); }
		});
		menuFileQuit.connect(new JMenuItem.Activate() {
			public void onActivate(JMenuItem JMenuItem) { menuFileQuit(); }
		});
		menuEditUndo.connect(new JMenuItem.Activate() {
			public void onActivate(JMenuItem JMenuItem) { JMenuEditUndo(); }
		});
		menuEditRedo.connect(new JMenuItem.Activate() {
			public void onActivate(JMenuItem JMenuItem) { JMenuEditRedo(); }
		});
		menuEditCut.connect(new JMenuItem.Activate() {
			public void onActivate(JMenuItem JMenuItem) { JMenuEditCut(); }
		});
		menuEditCopy.connect(new JMenuItem.Activate() {
			public void onActivate(JMenuItem JMenuItem) { JMenuEditCopy(); }
		});
		menuEditPaste.connect(new JMenuItem.Activate() {
			public void onActivate(JMenuItem JMenuItem) { JMenuEditPaste(); }
		});
		menuEditAdd.connect(new JMenuItem.Activate() {
			public void onActivate(JMenuItem JMenuItem) { JMenuEditAdd(); }
		});
		menuEditRemove.connect(new JMenuItem.Activate() {
			public void onActivate(JMenuItem JMenuItem) { JMenuEditRemove(); }
		});
		menuEditStart.connect(new JMenuItem.Activate() {
			public void onActivate(JMenuItem JMenuItem) { JMenuEditStart(); }
		});
		menuEditStop.connect(new JMenuItem.Activate() {
			public void onActivate(JMenuItem JMenuItem) { JMenuEditStop(); }
		});
	 	menuViewToolBar.connect(new JMenuItem.Activate() {
			public void onActivate(JMenuItem JMenuItem) { JMenuViewToolBar(); }
		});
		menuViewStatusBar.connect(new JMenuItem.Activate() {
			public void onActivate(JMenuItem JMenuItem) { JMenuViewStatusBar(); }
		});
		menuViewFullScreen.connect(new JMenuItem.Activate() {
			public void onActivate(JMenuItem JMenuItem) { JMenuViewFullScreen(); }
		});
		menuHelpAbout.connect(new JMenuItem.Activate() {
			public void onActivate(JMenuItem JMenuItem) { JMenuHelpAbout(); }
		});
		*/
		
		menuFile.add(menuFileNew);
		menuFile.add(menuFileOpen);
		menuFile.add(menuFileSave);
		menuFile.add(menuFileSaveAs);
		menuFile.add(new JSeparator());
		menuFile.add(menuFileClose);
		menuFile.add(menuFileQuit);
		menuEdit.add(menuEditUndo);
		menuEdit.add(menuEditRedo);
		menuEdit.add(new JSeparator());
		menuEdit.add(menuEditCut);
		menuEdit.add(menuEditCopy);
		menuEdit.add(menuEditPaste);
		menuEdit.add(new JSeparator());
		menuEdit.add(menuEditAdd);
		menuEdit.add(menuEditRemove);
		menuEdit.add(new JSeparator());
		menuEdit.add(menuEditStart);
		menuEdit.add(menuEditStop);
		menuView.add(menuViewToolBar);
		menuView.add(menuViewStatusBar);
		menuView.add(menuViewFullScreen);
		menuHelp.add(menuHelpAbout);

		
//		JMenuItem menuItemFile = new JMenuItem(Language.menuFile);
//		JMenuItem menuItemEdit = new JMenuItem(Language.menuEdit);
//		JMenuItem menuItemView = new JMenuItem(Language.menuView);
//		JMenuItem menuItemHelp = new JMenuItem(Language.menuHelp);
//
//		menuItemFile.add(menuFile);
//		menuItemEdit.add(menuEdit);
//		menuItemView.add(menuView);
//		menuItemHelp.add(menuHelp);

		this.menuBar.add(menuFile);
		this.menuBar.add(menuEdit);
		this.menuBar.add(menuView);
		this.menuBar.add(menuHelp);
		
		this.add(menuBar, BorderLayout.NORTH);
		
	}
	
	
	
	private void initDisplay () {
		this.area = new Area(new Dimension(500,500));
		this.add(area, BorderLayout.SOUTH);

		
		
	}
	
//	private void initStatusBar() {
//		this.statusBar = new JStatusbar();
//
//	}

	
	private void menuFileNew() { System.out.println("Clicou em: " +  Language.menuFileNew); }
	private void menuFileOpen() { System.out.println("Clicou em: " +  Language.menuFileOpen); }
	private void menuFileSave() { System.out.println("Clicou em: " +  Language.menuFileSave); }
	private void menuFileSaveAs() { System.out.println("Clicou em: " +  Language.menuFileSaveAs); }
	private void menuFileClose() { System.out.println("Clicou em: " +  Language.menuFileClose); }
	private void menuFileQuit() { System.out.println("Clicou em: " +  Language.menuFileQuit); }
	private void menuEditUndo() { System.out.println("Clicou em: " +  Language.menuEditUndo); }
	private void menuEditRedo() { System.out.println("Clicou em: " +  Language.menuEditRedo); }
	private void menuEditCut() { System.out.println("Clicou em: " +  Language.menuEditCut); }
	private void menuEditCopy() { System.out.println("Clicou em: " +  Language.menuEditCopy); }
	private void menuEditPaste() { System.out.println("Clicou em: " +  Language.menuEditPaste); }
	private void menuEditAdd() { System.out.println("Clicou em: " +  Language.menuEditAdd); }
	private void menuEditRemove() { System.out.println("Clicou em: " +  Language.menuEditRemove); }
	private void menuEditStart() { System.out.println("Clicou em: " +  Language.menuEditStart); }
	private void menuEditStop() { System.out.println("Clicou em: " +  Language.menuEditStop); }
	private void menuViewToolBar() { System.out.println("Clicou em: " +  Language.menuViewToolBar); }
	private void menuViewStatusBar() { System.out.println("Clicou em: " +  Language.menuViewStatusBar); }
	private void menuViewFullScreen() { System.out.println("Clicou em: " +  Language.menuViewFullScreen); }
	private void menuHelpAbout() { System.out.println("Clicou em: " +  Language.menuHelpAbout); }

	
	
//	public static void main(String args[]) {
//
//   	 try {
//			UIManager.setLookAndFeel(
//			            UIManager.getSystemLookAndFeelClassName());
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UnsupportedLookAndFeelException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		Simulator simulator = new Simulator();
//		
//	}

}
