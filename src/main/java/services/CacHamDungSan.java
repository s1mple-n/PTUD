package services;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.optionalusertools.PickerUtilities;
import ui.cauhinhchung.IDSBienMacDinh;
import ui.giaodienthongbaongoaivi.GDThongBaoKetQua;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.util.Locale;

public class CacHamDungSan implements IDSBienMacDinh {

    public static void datThuocTinhChoBtn(JButton btn, Color bgr, Color frg, Dimension dim){
        if (bgr == null){
            btn.setForeground(frg);
        }
        else{
            btn.setBorderPainted(false);
            btn.setBackground(bgr);
            btn.setForeground(frg);
        }

        btn.setPreferredSize(dim);
        btn.setMaximumSize(dim);
        btn.setFont(fntMacDinh);

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                btn.setBackground(bgrCacThanhPhanKhiDuocClickMacDinh);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                if (bgr != null){
                    btn.setBackground(bgr);
                }
                else{
                    btn.setBackground(bgrMacDinh);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }


    public static void datThuocTinhChoTxt(JTextField txt, String plcHolder, Dimension dim){
        txt.setFont(fntMacDinh);
        txt.setPreferredSize(dim);
        txt.setText(plcHolder);
        txt.setForeground(Color.gray);
        txt.setBackground(bgrMacDinh);
        txt.setBorder(
                BorderFactory.createCompoundBorder(
                        txt.getBorder(),
                        new EmptyBorder(0, 8, 0, 0)
                )
        );

        txt.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                String tuKhoa = txt.getText().trim();
                if (tuKhoa.equals(plcHolder)) {
                    txt.setText("");
                    txt.setForeground(frgMacDinh);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String tuKhoa = txt.getText().trim();
                if (tuKhoa.equals("")) {
                    txt.setText(plcHolder);
                    txt.setForeground(Color.GRAY);
                }
            }
        });
    }

    public static void datThuocTinhChoPwf(JPasswordField pwf, String plh, Dimension dim){
        pwf.setFont(fntMacDinh);
        pwf.setPreferredSize(dim);
        pwf.setText(plh);
        pwf.setForeground(Color.gray);
        pwf.setBackground(bgrMacDinh);
        pwf.setBorder(
                BorderFactory.createCompoundBorder(
                        pwf.getBorder(),
                        new EmptyBorder(0, 10, 0, 0)
                )
        );
        pwf.setEchoChar((char) 0);

        pwf.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                String tuKhoa = new String(pwf.getPassword());
                if (tuKhoa.equals(plh)) {
                    pwf.setText("");
                    pwf.setEchoChar('*');
                    pwf.setForeground(frgMacDinh);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String tuKhoa = new String(pwf.getPassword());
                if (tuKhoa.equals("")) {
                    pwf.setText(plh);
                    pwf.setEchoChar('\u0000');
                    pwf.setForeground(Color.GRAY);
                }
            }
        });
    }

    public static void nganCanViecNhapKiTuKhongPhaiSoVaoTxt(JTextField txt){
        txt.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                if (!Character.isDigit(evt.getKeyChar())) {
                    evt.consume();
                }
            }
        });
    }

    public static void datThuocTinhChoTblDuLieu(JTable tbl, Dimension dim, TableRowSorter trs){
        tbl.setDefaultEditor(Object.class, null);
        tbl.setRowHeight(chieuCaoHangDuLieuTrongTable);
        tbl.setFont(fntMacDinh);
        tbl.getTableHeader().setFont(fntMacDinh);
        tbl.setShowGrid(false);
        tbl.setGridColor(Color.WHITE);
        tbl.setRowSorter(trs);

        tbl.getTableHeader().setPreferredSize(
                new Dimension(
                        dim.width,
                        chieuCaoHangDuLieuTrongTable
                )
        );

        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tbl.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        tbl.getTableHeader().setOpaque(false);
        tbl.getTableHeader().setBackground(bgrTieuDeTable);
    }

    public static void datThuocTinhChoCacRad(JRadioButton rad){
        rad.setBackground(bgrMacDinh);
        rad.setFont(fntMacDinh);
        rad.setFocusPainted(false);

        rad.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                rad.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                rad.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rad.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rad.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    public static void datThuocTinhChoLbl(JLabel lbl, Font fnt, Color fgr){
        lbl.setFont(fnt);
        lbl.setForeground(fgr);
    }

    public static void duaTxtVeTrangThaiBanDau(JTextField txt, String plh){
        txt.setText(plh);
        txt.setForeground(Color.GRAY);
    }

    public static void duaTxtVeTrangThaiSanSangHienThiThongTin(JTextField txt, String plh){
        txt.setText(plh);
        txt.setForeground(frgMacDinh);
    }

    public static void duaPwfVeTrangThaiBanDau(JPasswordField pwf, String plh){
        pwf.setText(plh);
        pwf.setForeground(Color.GRAY);
        pwf.setEchoChar('\u0000');
    }

    public static void datThuocTinhChoTxp(JTextPane txp, boolean isCoVien, boolean isCanGiua, Color bgr, Dimension dim){
        if ( ! isCoVien ){
            txp.setBorder(
                    BorderFactory.createCompoundBorder(
                            txp.getBorder(),
                            BorderFactory.createEmptyBorder(0, 10, 0, 0)
                    )
            );
        }

        StyledDocument documentStyle = txp.getStyledDocument();

        if (isCanGiua){
            SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
            StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
            documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);
        }
        else{
            SimpleAttributeSet leftAttribute = new SimpleAttributeSet();
            StyleConstants.setAlignment(leftAttribute, StyleConstants.ALIGN_LEFT);
            documentStyle.setParagraphAttributes(0, documentStyle.getLength(), leftAttribute, false);
        }

        txp.setBackground(bgrMacDinh);
        txp.setPreferredSize(dim);
        txp.setForeground(frgMacDinh);
        txp.setFont(fntMacDinh);
        txp.setBackground(bgr);
    }

    public static void dungPnlTieuDeLoc(JPanel pnl, JLabel lbl){
        pnl.setBackground(bgrTieuDeThanhTienIch);
        pnl.setPreferredSize(dimPnlTieuDeThanhTienIch);
        pnl.setLayout(new FlowLayout(
                FlowLayout.LEFT,
                khoangCachSoVoiLeTraiCuaTieuDe,
                khoangCachSoVoiLeTrenCuaTieuDe - 2
        ));

        lbl.setForeground(bgrMacDinh);
        lbl.setFont(fntTieuDeThanhTienIch);

        pnl.add(lbl);
    }

    public static void dungPnlNgayBatDauHoacKetThuc(JPanel pnl, JLabel lbl, DatePicker dtp, Dimension dimPnl, Dimension dimDatePicker){
        pnl.setBackground(bgrMacDinh);
        pnl.setLayout(new FlowLayout(
                FlowLayout.LEFT,
                khoangCachSoVoiLeTraiCuaTieuDe,
                2
        ));
        pnl.setPreferredSize(dimPnl);

        lbl.setFont(fntMacDinh);
        lbl.setForeground(frgMacDinh);
        pnl.add(lbl);

        datThuocTinhMacDinhChoDatePicker(dtp, dimDatePicker);
        pnl.add(dtp);
    }

    private static void datThuocTinhMacDinhChoDatePicker(DatePicker dtp, Dimension dim){
        ImageIcon imi = new ImageIcon(
                Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Calendar_24px_1.png")
        );

        JButton btnDtp = dtp.getComponentToggleCalendarButton();
        btnDtp.setText("");
        btnDtp.setIcon(imi);

        DatePickerSettings dps = new DatePickerSettings(
                new Locale("vi", "vn")
        );

        dps.setAllowKeyboardEditing(false);
        dps.setFontCalendarDateLabels(fntMacDinh);
        dps.setFontMonthAndYearNavigationButtons(fntMacDinh);
        dps.setFontMonthAndYearMenuLabels(fntMacDinh);
        dps.setFontCalendarWeekdayLabels(fntMacDinh);
        dps.setFontValidDate(fntMacDinh);
        dps.setFontTodayLabel(fntMacDinh);
        dps.setFontClearLabel(fntMacDinh);

        dtp.setSettings(dps);
        dtp.setPreferredSize(dim);
        dtp.setDateToToday();
    }

    public static void datPhimTatXoaTrangCtrlDChoTxt(JTextField txt){
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txt.setText("");
            }
        };

        String phimTat = "control D";
        KeyStroke keyStroke = KeyStroke.getKeyStroke(phimTat);
        txt.getInputMap().put(keyStroke, phimTat);
        txt.getActionMap().put(phimTat, action);
    }

    public static void datPhimTatXoaTrangCtrlDChoTxa(JTextArea txa){
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txa.setText("");
            }
        };

        String phimTat = "control D";
        KeyStroke keyStroke = KeyStroke.getKeyStroke(phimTat);
        txa.getInputMap().put(keyStroke, phimTat);
        txa.getActionMap().put(phimTat, action);
    }

    public static void datPhimTatXoaTrangCtrlDChoPwf(JPasswordField pwf){
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pwf.setText("");
            }
        };

        String phimTat = "control D";
        KeyStroke keyStroke = KeyStroke.getKeyStroke(phimTat);
        pwf.getInputMap().put(keyStroke, phimTat);
        pwf.getActionMap().put(phimTat, action);
    }

    public static void datThuocTinhChoTxa(JTextArea txa, Dimension dim, boolean isEditable){
        txa.setBackground(Color.WHITE);
        txa.setPreferredSize(dim);
        txa.setForeground(frgMacDinh);
        txa.setFont(fntMacDinh);
        txa.setLineWrap(true);
        txa.setWrapStyleWord(true);
        txa.setEditable(isEditable);
    }

    public static void datMauVienChoTxtDuocPhepChinhSua(JTextField txt){
        Border vienMacDinh = BorderFactory.createLineBorder(bgrVienThanhPhanDuocPhepSua, 2, false);
        Border vienCachDieu = BorderFactory.createEmptyBorder(0, 10, 0, 0);
        Border vienChinhThuc = new CompoundBorder(vienMacDinh, vienCachDieu);

        txt.setBorder(vienChinhThuc);
        txt.setForeground(frgMacDinh);
        txt.setEditable(true);
    }

    public static void datMauVienChoTxaDuocPhepChinhSua(JTextArea txa){
        Border vienMacDinh = BorderFactory.createLineBorder(bgrVienThanhPhanDuocPhepSua, 2, false);
        Border vienCachDieu = BorderFactory.createEmptyBorder(0, 10, 0, 0);
        Border vienChinhThuc = new CompoundBorder(vienMacDinh, vienCachDieu);

        txa.setBorder(vienChinhThuc);
        txa.setForeground(frgMacDinh);
        txa.setEditable(true);
    }

    public static void datThuocTinhChoCbb(JComboBox cbb, Dimension dim) {
        cbb.setSelectedIndex(0);
        cbb.setBackground(bgrMacDinh);
        cbb.setPreferredSize(dim);
        cbb.setForeground(frgMacDinh);
        cbb.setFont(fntMacDinh);
        cbb.setEditable(false);
    }

    public static TimePicker traVeTimePicker(Dimension dim){
        Locale vn = new Locale("vi", "vn");
        TimePickerSettings tps = new TimePickerSettings(vn);
        tps.setFormatForDisplayTime(PickerUtilities.createFormatterFromPatternString(
                "HH:mm", tps.getLocale()));
        tps.setFormatForMenuTimes(PickerUtilities.createFormatterFromPatternString(
                "HH:mm", tps.getLocale()));
        tps.initialTime = LocalTime.now();

        TimePicker timePicker = new TimePicker(tps);
        timePicker.setPreferredSize(dim);
        timePicker.setBackground(bgrMacDinh);

        JButton btnTimePicker = timePicker.getComponentToggleTimeMenuButton();
        btnTimePicker.setText("");
        btnTimePicker.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().getImage("src/main/resources/BieuTuong/Clock_24px_1.png")
        ));

        timePicker.getComponentTimeTextField().setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                        BorderFactory.createEmptyBorder(0, 10, 0, 0)
                )
        );
        return timePicker;
    }

    public static void duaTimePickerVeTrangThaiBanDau(TimePicker timePicker){
        timePicker.setTime(LocalTime.now());
    }

    public static void duaTxaVeTrangThaiSanSangHienThiThongTin(JTextArea txa, String plh){
        txa.setText(plh);
        txa.setForeground(frgMacDinh);
    }

    public static void hienThiThongBaoKetQua(int loaiThongBao, String thongBao){
        SwingUtilities.invokeLater(() -> {
            GDThongBaoKetQua gdThongBaoKetQua = GDThongBaoKetQua.getGdThongBaoKetQua();

            gdThongBaoKetQua.cungCapLoaiThongBaoVaThongDiep(
                    loaiThongBao,
                    thongBao
            );

            gdThongBaoKetQua.setVisible(true);
        });
    }

    public static void hienThiThongBaoKhongCoDuLieuPhuHop(){
        SwingUtilities.invokeLater(() -> {
            GDThongBaoKetQua gdThongBaoKetQua = GDThongBaoKetQua.getGdThongBaoKetQua();

            gdThongBaoKetQua.cungCapLoaiThongBaoVaThongDiep(
                    GDThongBaoKetQua.THONG_BAO_LOI,
                    "Không có dữ liệu phù hợp!"
            );

            gdThongBaoKetQua.setVisible(true);
        });
    }

    public static boolean kiemTraHangTrongTableDuocChonCoPhaiLaHangTongKetKhong(JTable tbl, int hang){
        return tbl.getValueAt(hang, 0).toString().equals("Tổng cộng:");
    }
}
