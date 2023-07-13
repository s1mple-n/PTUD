package ui.cauhinhchung;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.File;

public class CaNhanHoaLookAndFeel implements IDSBienMacDinh {

    public static void caNhanHoaLookAndFeel(){
        try {
            FlatLightLaf.setup();
            UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch (Exception ex){
            ex.printStackTrace();
        }

        khoiTaoFontHelveticaWorld();

        datCacGiaTriMacDinh();
    }

    private static void datCacGiaTriMacDinh(){
        UIManager.put("TextField.font", fntMacDinh);

        UIManager.put("List.font", fntMacDinh);

        UIManager.put("OptionPane.font", fntMacDinh);
        UIManager.put("OptionPane.background", bgrMacDinh);
        UIManager.put("OptionPane.buttonFont", fntMacDinh);
        UIManager.put("OptionPane.messageFont", fntMacDinh);
        UIManager.put("OptionPane.questionIcon", new ImageIcon(
                Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Warning_48px_3.png")
        ));
        UIManager.put("OptionPane.warningIcon", new ImageIcon(
                Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Warning_48px_3.png")
        ));

        UIManager.put("Panel.background", bgrMacDinh);

        UIManager.put("PopupMenu.background", bgrMacDinh);

        UIManager.put("ComboBox.font", fntMacDinh);

        UIManager.put("MenuItem.background", bgrMacDinh);
        UIManager.put("MenuItem.borderPainted", null);
        UIManager.put("MenuItem.font", fntMacDinh);

        UIManager.put( "Component.arrowType", "chevron");
        UIManager.put( "Component.focusWidth", 1 );
        UIManager.put( "ScrollBar.showButtons", true );
        UIManager.put( "ScrollBar.thumbArc", 15 );
        UIManager.put( "ScrollBar.thumbInsets", new Insets( 2, 2, 2, 2 ) );
        UIManager.put( "TextComponent.arc", 15 );
        UIManager.put( "Component.arc", 15 );
        UIManager.put( "Button.arc", 15 );
        UIManager.put( "Button.font", fntMacDinh );

        UIManager.put( "TabbedPane.selectedBackground", new Color(135, 194, 224) );
    }

    private static void khoiTaoFontHelveticaWorld(){
        Font font = null;

        try {
            font = Font.createFont(
            		Font.TRUETYPE_FONT, 
            		new File(fontFile)
            		);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
