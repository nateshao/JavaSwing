package com.nateshao.view;

import com.nateshao.dao.UserDao;
import com.nateshao.model.User;
import com.nateshao.utils.DBUtils;
import com.nateshao.utils.StringUtils;

import javax.swing.GroupLayout.Alignment;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;
import javax.swing.LayoutStyle.ComponentPlacement;
/**
 * @date Created by ��ͩ�� on 2022/6/6 16:47
 * @΢�Ź��ں� ǧ��ı��ʱ��
 * @������վ www.nateshao.cn
 * @���� https://nateshao.gitlab.io
 * @GitHub https://github.com/nateshao
 * @Gitee https://gitee.com/nateshao
 * Description:
 */
public class LogOnFrm extends JFrame {

    private JPanel contentPane;
    private static JTextField userNameTxt;
    private static JPasswordField passwordTxt;
    private DBUtils dbUtil = new DBUtils();
    private UserDao userDao = new UserDao();
    private static JCheckBox rememberChe;
    private static boolean isSelected;
    static Properties pro = new Properties();
    private static String username = "";
    private static String password = "";
    static BufferedWriter out = null;
    static ClassLoader classLoader;

    /**
     * Launch the application.
     *
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LogOnFrm frame = new LogOnFrm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //�����û�ѡ��ļ�ס����ѡ��
        classLoader = LogOnFrm.class.getClassLoader();
        pro.load(classLoader.getResourceAsStream("user_information.properties"));
        isSelected = Boolean.parseBoolean(pro.getProperty("isSelected"));
        username = pro.getProperty("username");
        password = pro.getProperty("password");
        System.out.println(isSelected + " " + username + " " + password);
    }

    /**
     * Create the frame.
     *
     * @throws IOException
     */
    public LogOnFrm() throws IOException {
        setResizable(false);
        setTitle("\u767B\u5F55\u754C\u9762");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 574, 404);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel label = new JLabel("\u7528\u6237\u767B\u5F55\u548C\u6CE8\u518C");
        label.setFont(new Font("����", Font.PLAIN, 20));

        JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D:");
        lblNewLabel.setFont(new Font("����", Font.PLAIN, 20));

        JLabel label_1 = new JLabel("\u5BC6\u7801:");
        label_1.setFont(new Font("����", Font.PLAIN, 20));

        userNameTxt = new JTextField();
        userNameTxt.setFont(new Font("����", Font.PLAIN, 20));
        userNameTxt.setColumns(10);

        passwordTxt = new JPasswordField();
        passwordTxt.setFont(new Font("����", Font.PLAIN, 20));
        passwordTxt.setColumns(10);

        rememberChe = new JCheckBox("\u8BB0\u4F4F\u5BC6\u7801");
        rememberChe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {       //checkBox ʱ����Ӧ
                rememberPassword(e);
            }
        });
        JButton btnNewButton = new JButton("\u767B\u5F55");
        //��¼�¼�
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginAction(e);
            }
        });

        btnNewButton.setFont(new Font("����", Font.PLAIN, 20));
        JButton button = new JButton("\u6CE8\u518C");

        //ע���¼�
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerAction(e);
            }
        });

        button.setFont(new Font("����", Font.PLAIN, 20));
        JLabel lbldblogin = new JLabel("\u6570\u636E\u5E93\u540D:login");
        JLabel lblTuser = new JLabel("\u8868\u540D: t_user");
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(lblTuser, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                                .addGap(96)
                                                .addComponent(label, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(90)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addGap(61)
                                                                .addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                                                                .addComponent(button, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(label_1, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                                        .addComponent(rememberChe, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(passwordTxt, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))))))
                                .addGap(142))
                        .addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbldblogin)
                                .addContainerGap(429, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbldblogin)
                                .addGap(10)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(label, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblTuser))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(rememberChe)
                                .addGap(26)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(button, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
                                .addContainerGap(37, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
        //���ô��������ʾ
        this.setLocationRelativeTo(null);
        initSelected();
    }

    //ע�᷽����ʵ��
    private void registerAction(ActionEvent e) {
        dispose();
        new RegisterFrm().setVisible(true);
    }
    //��¼������ʵ��
    private void loginAction(ActionEvent e) {
        String userName = userNameTxt.getText().toString();
        String password = new String(passwordTxt.getPassword());
        if (StringUtils.isEmpty(userName)) { //�ж��û����Ƿ�Ϊ��
            JOptionPane.showMessageDialog(null, "�û�������Ϊ�գ�"); //����û���Ϊ�� ����ʾ�û�
            return;
        }
        if (StringUtils.isEmpty(password)) {
            JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�"); //�������Ϊ�� ����ʾ�û�
            return;
        }
        User user = new User(userName, password);
        Connection con = null;
        try {
            con = dbUtil.getCon();
            User current = userDao.login(con, user);
            if (current != null) {
                dispose();
                new MainFrm(current).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "�û������������"); //����û���Ϊ�� ����ʾ�û�
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }

    }


    private void rememberPassword(ActionEvent e) {

        try {
            out = new BufferedWriter(new FileWriter("F:\\GitHub--Gitee\\2022-test\\login\\src\\user_information.properties"));
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if (rememberChe.isSelected()) {  //ѡ��״̬
            System.out.println("ѡ��");
            isSelected = true;

            pro.setProperty("isSelected", "true");
            pro.setProperty("username", userNameTxt.getText());
            pro.setProperty("password", passwordTxt.getText());
            try {
                pro.store(out, "");
                System.out.println("����Pro�ɹ�");
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }


        } else {
            System.out.println("δѡ��");
            isSelected = false;
            pro.setProperty("isSelected", "false");
            pro.setProperty("username", "");
            pro.setProperty("password", "");
            try {
                pro.store(out, "");
                System.out.println("����Pro�ɹ�");
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
    public static void initSelected() throws IOException {
        userNameTxt.setText(username);
        passwordTxt.setText(password);
        rememberChe.setSelected(isSelected);
    }
}
