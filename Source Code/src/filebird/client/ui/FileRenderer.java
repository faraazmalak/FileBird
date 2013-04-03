/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filebird.client.ui;

import java.awt.Component;
import java.io.File;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Faraaz Malak
 */
class FileRenderer extends DefaultListCellRenderer {

    private boolean pad;
    private Border padBorder = new EmptyBorder(3, 3, 3, 3);

    FileRenderer(boolean pad) {
        this.pad = pad;
    }

    @Override
    public Component getListCellRendererComponent(
            JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

        Component c = super.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus);
        JLabel l = (JLabel) c;
        File f = (File) value;
        l.setText(f.getName());
        l.setIcon(FileSystemView.getFileSystemView().getSystemIcon(f));
        if (pad) {
            l.setBorder(padBorder);
        }

        return l;
    }
}