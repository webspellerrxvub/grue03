/*-
 * Copyright (C) 2008 Erik Larsson
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.catacombae.hfsexplorer.testcode.editgpt;

import java.awt.BorderLayout;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import org.catacombae.hfsexplorer.partitioning.GUIDPartitionTable;
import org.catacombae.hfsexplorer.partitioning.MutableGUIDPartitionTable;
import org.catacombae.hfsexplorer.win32.WindowsLowLevelIO;
import org.catacombae.hfsexplorer.win32.WritableWin32File;
import org.catacombae.io.FileStream;
import org.catacombae.io.RandomAccessStream;
import org.catacombae.io.ReadableFileStream;
import org.catacombae.io.ReadableRandomAccessStream;

/**
 *
 * @author  Erik
 */
public class EditGPT extends javax.swing.JPanel {
    private GUIDPartitionTable originalGPT = null;
    private MutableGUIDPartitionTable modifiedGPT = null;
    private ContainerPanel containerPanel;

    /** Creates new form EditGPT */
    public EditGPT() {
        containerPanel = new ContainerPanel();
        
        initComponents();
        
        add(containerPanel, BorderLayout.CENTER);
    }
    
    public void loadFile(File f) {
        ReadableRandomAccessStream llf;
	if(WritableWin32File.isSystemSupported())
	    llf = new WindowsLowLevelIO(f.getAbsolutePath());
	else
	    llf = new ReadableFileStream(f.getAbsolutePath());
        
        final GUIDPartitionTable gpt = new GUIDPartitionTable(llf, 0);
        
        setFields(gpt);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents

    private void setFields(GUIDPartitionTable gpt) {
        this.originalGPT = gpt;
        //this.modifiedGPT = new MutableGUIDPartitionTable(gpt);
        
        containerPanel.setFields(originalGPT.getStructElements());
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
    public static void main(String[] args) {
        try {
	    javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
	    /*
	     * Description of look&feels:
	     *   http://java.sun.com/docs/books/tutorial/uiswing/misc/plaf.html
	     */
	}
	catch(Throwable e) {
	    //It's ok. Non-critical.
	}
        
        File gptFile = new File(args[0]);
        
        JFrame jf = new JFrame("EditGPT");
        EditGPT egpt = new EditGPT();
        jf.add(new JScrollPane(egpt));
        egpt.loadFile(gptFile);
        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
