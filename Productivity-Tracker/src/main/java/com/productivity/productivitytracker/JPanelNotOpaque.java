import javax.swing.*;
import java.awt.*;

public class JPanelNotOpaque extends JPanel {
    public JPanelNotOpaque(FlowLayout flowLayout){
        this.setOpaque(false);
    }

    public JPanelNotOpaque(){
        this.setOpaque(false);
    }


    public JPanelNotOpaque(GridBagLayout gridBagLayout){
        this.setOpaque(false);
    }


}
