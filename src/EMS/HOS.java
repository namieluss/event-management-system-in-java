/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EMS;

/**
 *
 * @author Suleiman
 */

import Database.Database;
import Service.HOS_Service;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

public class HOS extends javax.swing.JFrame {

    public Database data;
    public HOS_Service srv;

    private final String name;
    private final String role;

    public HOS(String name, String role) {
        this.name = name;
        this.role = role;
        this.setResizable(false);
        this.setTitle("Welcome "+this.name);
        this.setLocationRelativeTo(null);

        initComponents();
        program_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        event_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        program_name = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        program_details = new javax.swing.JTextArea();
        add_new_program = new javax.swing.JButton();
        lecturers = new javax.swing.JComboBox();
        assign_coordinator = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        approve_event = new javax.swing.JButton();
        decline_event = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        program_list = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        event_list = new javax.swing.JList();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        refresh = new javax.swing.JButton();
        refresh_program = new javax.swing.JButton();
        logout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        program_name.setText("program_name");
        program_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                program_nameActionPerformed(evt);
            }
        });

        jLabel1.setText("Add New Program");

        program_details.setColumns(20);
        program_details.setRows(5);
        jScrollPane1.setViewportView(program_details);

        add_new_program.setText("Add new Program");
        add_new_program.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_new_programActionPerformed(evt);
            }
        });

        lecturers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lecturersActionPerformed(evt);
            }
        });

        assign_coordinator.setText("Assign");
        assign_coordinator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assign_coordinatorActionPerformed(evt);
            }
        });

        jLabel2.setText("Assign New Program Coordinator");

        approve_event.setText("Approve");
        approve_event.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approve_eventActionPerformed(evt);
            }
        });

        decline_event.setText("Decline");

        jScrollPane4.setViewportView(program_list);

        jScrollPane3.setViewportView(event_list);

        jLabel3.setText("Approve Events");

        refresh.setText("Refresh");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        refresh_program.setText("Refresh");
        refresh_program.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refresh_programActionPerformed(evt);
            }
        });

        logout.setText("Logout");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(add_new_program, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(program_name)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(assign_coordinator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(refresh_program, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lecturers, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(logout))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(approve_event, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(decline_event, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refresh)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(logout)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(program_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lecturers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(add_new_program)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(assign_coordinator)
                        .addComponent(refresh_program)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(approve_event)
                    .addComponent(decline_event))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void program_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_program_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_program_nameActionPerformed

    private void lecturersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lecturersActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lecturersActionPerformed

    private void add_new_programActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_new_programActionPerformed
        srv = new HOS_Service(this);

        String p_name = program_name.getText();
        String details = program_details.getText();

        if (!p_name.equals("") && !details.equals("")) {
            if (!srv.check_program(p_name)) {
                srv.add_new_program(p_name, details);
            } else {
                JOptionPane.showMessageDialog(null, "Program Already exists");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Enter All fields");
        }
    }//GEN-LAST:event_add_new_programActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        srv = new HOS_Service(this);
        srv.list_all_event();
    }//GEN-LAST:event_refreshActionPerformed

    private void refresh_programActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refresh_programActionPerformed
        srv = new HOS_Service(this);
        srv.list_all_lecturers();
        srv.list_all_program();
    }//GEN-LAST:event_refresh_programActionPerformed

    private void assign_coordinatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assign_coordinatorActionPerformed
        int[] selectedIx = program_list.getSelectedIndices();
        int idx = program_list.getSelectedIndex();

        String lect = String.valueOf(lecturers.getSelectedItem());
        String prog = (String) program_list.getSelectedValue();

        if (idx != -1 && lect != null) {            
            srv.add_program_coordinator( prog, lect);
        } else {
            JOptionPane.showMessageDialog(null, "Select Programe please");
        }
    }//GEN-LAST:event_assign_coordinatorActionPerformed

    private void approve_eventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approve_eventActionPerformed
        srv = new HOS_Service(this);
        int idx = event_list.getSelectedIndex();
         String prog = (String) event_list.getSelectedValue();
        
        if (idx != -1 ) {         
            srv.event_management(prog, "approve");
        } else {
            JOptionPane.showMessageDialog(null, "Select Programe please");
        }
    }//GEN-LAST:event_approve_eventActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_logoutActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_new_program;
    private javax.swing.JButton approve_event;
    private javax.swing.JButton assign_coordinator;
    private javax.swing.JButton decline_event;
    public javax.swing.JList event_list;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public javax.swing.JComboBox lecturers;
    private javax.swing.JButton logout;
    public javax.swing.JTextArea program_details;
    public javax.swing.JList program_list;
    private javax.swing.JTextField program_name;
    private javax.swing.JButton refresh;
    private javax.swing.JButton refresh_program;
    // End of variables declaration//GEN-END:variables
}
