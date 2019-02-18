import javax.swing.SwingUtilities;
//главный метод
public class TestMain {

	public static void main(String[] args) {
		
		
		SwingUtilities.invokeLater(new  Runnable() {
		      public void run() {
		        new  Gui();
		 
		      }
		    });  
	}

}
