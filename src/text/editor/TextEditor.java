package text.editor;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class TextEditor extends JFrame implements ActionListener {

    MenuBar menu_bar;
    Menu file, edit, format, font, font1, font2;
    MenuItem item1, item2, item3, item4, item5,
            item6, item7, item8, item9, item10,
            fname1, fname2, fname3, fname4,
            fstyle1, fstyle2, fstyle3, fstyle4,
            fsize1, fsize2, fsize3, fsize4;

    JPanel main_panel;
    TextArea text;

    Font f;
    String months[] = {
        "Jan", "Feb", "Mar", "Apr",
        "May", "Jun", "Jul", "Aug",
        "Sep", "Oct", "Nov", "Dec"};

    GregorianCalendar gc;

    String command = " ";
    String str = " ";

    String str1 = " ", str2 = " ", str3 = " ", str4 = " ",
           str6 = " ", str7 = " ", str8 = " ", str9 = " ";

    int len1;
    int i = 0;
    int pos1;
    int len;

    public TextEditor(String str) {

        super(str);

        main_panel = new JPanel();
        main_panel = (JPanel) getContentPane();
        main_panel.setLayout(new FlowLayout());

        menu_bar = new MenuBar();
        setMenuBar(menu_bar);

        file = new Menu("File");
        edit = new Menu("Edit");
        format = new Menu("Format");
        font = new Menu("Font");
        font1 = new Menu("Font Style");
        font2 = new Menu("Size");

        file.add(item1 = new MenuItem("New..."));
        file.add(item2 = new MenuItem("Open"));
        file.add(item3 = new MenuItem("Save As..."));
        file.add(item4 = new MenuItem("Exit"));
        menu_bar.add(file);

        edit.add(item5 = new MenuItem("Cut"));
        edit.add(item6 = new MenuItem("Copy"));
        edit.add(item7 = new MenuItem("Paste"));
        edit.add(item8 = new MenuItem("Delete"));
        edit.add(item10 = new MenuItem("Select All"));
        edit.add(item9 = new MenuItem("Time/Date"));
        menu_bar.add(edit);

        format.add(font);
        format.add(font1);
        format.add(font2);

        font.add(fname1 = new MenuItem("Courier"));
        font.add(fname2 = new MenuItem("Sans Serif"));
        font.add(fname3 = new MenuItem("Monospaced"));
        font.add(fname4 = new MenuItem("Symbol"));

        font1.add(fstyle1 = new MenuItem("Regular"));
        font1.add(fstyle2 = new MenuItem("Bold"));
        font1.add(fstyle3 = new MenuItem("Italic"));
        font1.add(fstyle4 = new MenuItem("Bold Italic"));

        font2.add(fsize1 = new MenuItem("12"));
        font2.add(fsize2 = new MenuItem("14"));
        font2.add(fsize3 = new MenuItem("18"));
        font2.add(fsize4 = new MenuItem("20"));

        menu_bar.add(format);

        item1.addActionListener(this);
        item2.addActionListener(this);
        item3.addActionListener(this);
        item4.addActionListener(this);
        item5.addActionListener(this);
        item6.addActionListener(this);
        item7.addActionListener(this);
        item8.addActionListener(this);
        item9.addActionListener(this);
        item10.addActionListener(this);
        fname1.addActionListener(this);
        fname2.addActionListener(this);
        fname3.addActionListener(this);
        fname4.addActionListener(this);
        fstyle1.addActionListener(this);
        fstyle2.addActionListener(this);
        fstyle3.addActionListener(this);
        fstyle4.addActionListener(this);
        fsize1.addActionListener(this);
        fsize2.addActionListener(this);
        fsize3.addActionListener(this);
        fsize4.addActionListener(this);

        text = new TextArea(26, 60);
        main_panel.add(text);

        f = new Font("Monotype Corsiva", Font.PLAIN, 15);
        text.setFont(f);
    }

    public void actionPerformed(ActionEvent ae) {

        command = (String) ae.getActionCommand();

        if (command.equals("New...")) {
            dispose();
            TextEditor note1 = new TextEditor("Untitled-Notepad");
            note1.setSize(500, 500);
            note1.setVisible(true);
        }

        try {

            if (command.equals("Open")) {

                str4 = " ";
                FileDialog dialog = new FileDialog(this, "Open");
                dialog.setVisible(true);

                str1 = dialog.getDirectory();
                str2 = dialog.getFile();
                str3 = str1 + str2;
                File f = new File(str3);
                FileInputStream fobj = new FileInputStream(f);
                len = (int) f.length();
                for (int j = 0; j < len; j++) {
                    char str5 = (char) fobj.read();
                    str4 = str4 + str5;

                }

                text.setText(str4);

            }
        } catch (IOException e) {
        }

        try {

            if (command.equals("Save As...")) {
                FileDialog fdialog1 = new FileDialog(this, "Save As", FileDialog.SAVE);
                fdialog1.setVisible(true);

                str7 = fdialog1.getDirectory();
                str8 = fdialog1.getFile();
                str9 = str7 + str8;

                str6 = text.getText();
                len1 = str6.length();
                byte buf[] = str6.getBytes();

                File f1 = new File(str9);
                FileOutputStream fobj1 = new FileOutputStream(f1);
                for (int k = 0; k < len1; k++) {
                    fobj1.write(buf[k]);
                }
                fobj1.close();
            }

            this.setTitle(str8);

        } catch (IOException e) {
        }

        if (command.equals("Exit")) {
            System.exit(0);
        }

        if (command.equals("Cut")) {
            str = text.getSelectedText();
            i = text.getText().indexOf(str);
            text.replaceRange(" ", i, i + str.length());
        }

        if (command.equals("Copy")) {
            str = text.getSelectedText();
        }

        if (command.equals("Paste")) {
            pos1 = text.getCaretPosition();
            text.insert(str, pos1);
        }
        if (command.equals("Delete")) {
            String msg = text.getSelectedText();
            i = text.getText().indexOf(msg);
            text.replaceRange(" ", i, i + msg.length());
        }
        if (command.equals("Time/Date")) {
            gc = new GregorianCalendar();
            String h = String.valueOf(gc.get(Calendar.HOUR));
            String m = String.valueOf(gc.get(Calendar.MINUTE));
            String s = String.valueOf(gc.get(Calendar.SECOND));
            String date = String.valueOf(gc.get(Calendar.DATE));
            String mon = months[gc.get(Calendar.MONTH)];
            String year = String.valueOf(gc.get(Calendar.YEAR));
            String hms = "Time" + " - " + h + ":" + m + ":" + s + "  Date" + "  -  " + date + "  " + mon + " " + year;
            int loc = text.getCaretPosition();
            text.insert(hms, loc);
        }
        if (command.equals("Courier")) {

            String font_name = f.getName();
            String font_family = f.getFamily();
            int fontSize = f.getSize();
            int fontStyle = f.getStyle();

            f = new Font("Courier", fontStyle, fontSize);
            text.setFont(f);
        }
        if (command.equals("Sans Serif")) {
            String font_name = f.getName();
            String font_family = f.getFamily();
            int fontSize = f.getSize();
            int fontStyle = f.getStyle();

            f = new Font("Sans Serif", fontStyle, fontSize);
            text.setFont(f);
        }
        if (command.equals("Monospaced")) {
            String font_name = f.getName();
            String font_family = f.getFamily();
            int fontSize = f.getSize();
            int fontStyle = f.getStyle();

            f = new Font("Monospaced", fontStyle, fontSize);
            text.setFont(f);
        }

        if (command.equals("Symbol")) {
            String font_name = f.getName();
            String font_family = f.getFamily();
            int fontSize = f.getSize();
            int fontStyle = f.getStyle();

            f = new Font("Symbol", fontStyle, fontSize);
            text.setFont(f);
            System.out.println(f.getFamily());
        }
        if (command.equals("Regular")) {
            String font_name = f.getName();
            String font_family = f.getFamily();
            int fontSize = f.getSize();
            int fontStyle = f.getStyle();

            f = new Font(font_name, Font.PLAIN, fontSize);
            text.setFont(f);
        }
        if (command.equals("Bold")) {
            String font_name = f.getName();
            String font_family = f.getFamily();
            int fontSize = f.getSize();
            int fontStyle = f.getStyle();

            f = new Font(font_name, Font.BOLD, fontSize);
            text.setFont(f);
        }
        if (command.equals("Italic")) {
            String font_name = f.getName();
            String font_family = f.getFamily();
            int fontSize = f.getSize();
            int fontStyle = f.getStyle();

            f = new Font(font_name, Font.ITALIC, fontSize);
            text.setFont(f);
        }
        if (command.equals("Bold Italic")) {
            String font_name = f.getName();
            String font_family = f.getFamily();
            int fontSize = f.getSize();
            int fontStyle = f.getStyle();

            f = new Font(font_name, Font.BOLD | Font.ITALIC, fontSize);
            text.setFont(f);
        }

        if (command.equals("12")) {
            String font_name = f.getName();
            String font_family = f.getFamily();
            int fontSize = f.getSize();
            int fontStyle = f.getStyle();

            f = new Font(font_name, fontStyle, 12);
            text.setFont(f);
        }

        if (command.equals("14")) {
            String font_name = f.getName();
            String font_family = f.getFamily();
            int fontSize = f.getSize();
            int fontStyle = f.getStyle();

            f = new Font(font_name, fontStyle, 14);
            text.setFont(f);
        }
        if (command.equals("18")) {
            String font_name = f.getName();
            String font_family = f.getFamily();
            int fontSize = f.getSize();
            int fontStyle = f.getStyle();

            f = new Font(font_name, fontStyle, 18);
            text.setFont(f);
        }
        if (command.equals("20")) {
            String font_name = f.getName();
            String font_family = f.getFamily();
            int fontSize = f.getSize();
            int fontStyle = f.getStyle();

            f = new Font(font_name, fontStyle, 20);
            text.setFont(f);
        }
        if (command.equals("Select All")) {
            String strText = text.getText();
            int strLen = strText.length();
            text.select(0, strLen);
        }

    }

    public static void main(String args[]) {
        TextEditor note = new TextEditor("Untitled-Notepad");
        note.setSize(500, 500);
        note.setVisible(true);
    }
}
