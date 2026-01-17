package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainApp extends JFrame {

    private JTextField txtNama, txtHp, txtAlamat;
    private JTextArea areaOutput;
    private JComboBox<String> comboPaket;

    private ArrayList<String> daftarPeserta = new ArrayList<>();
    private String paketDipilih = "";

    public MainApp() {

        setTitle("Sistem Manajemen Operasional Tour Bali");
        setSize(500, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        /* ===== PANEL INPUT ===== */
        JPanel panelInput = new JPanel(new GridLayout(4, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createTitledBorder("Form Peserta"));

        comboPaket = new JComboBox<>(new String[]{"Reguler", "Premium"});
        txtNama = new JTextField();
        txtHp = new JTextField();
        txtAlamat = new JTextField();

        panelInput.add(new JLabel("Pilih Paket"));
        panelInput.add(comboPaket);

        panelInput.add(new JLabel("Nama Peserta"));
        panelInput.add(txtNama);

        panelInput.add(new JLabel("Nomor HP"));
        panelInput.add(txtHp);

        panelInput.add(new JLabel("Alamat"));
        panelInput.add(txtAlamat);

        /* ===== PANEL BUTTON ===== */
        JButton btnTambah = new JButton("Tambah Peserta");
        JButton btnLihat = new JButton("Lihat Peserta");
        JButton btnHapus = new JButton("Hapus Peserta");

        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panelButton.add(btnTambah);
        panelButton.add(btnLihat);
        panelButton.add(btnHapus);

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelInput, BorderLayout.CENTER);
        panelAtas.add(panelButton, BorderLayout.SOUTH);

        add(panelAtas, BorderLayout.NORTH);

        /* ===== OUTPUT ===== */
        areaOutput = new JTextArea();
        areaOutput.setEditable(false);
        areaOutput.setBorder(BorderFactory.createTitledBorder("Daftar Peserta"));
        add(new JScrollPane(areaOutput), BorderLayout.CENTER);

        /* ===== ACTION ===== */
        btnTambah.addActionListener(e -> tambahPeserta());
        btnLihat.addActionListener(e -> tampilkanPeserta());
        btnHapus.addActionListener(e -> hapusPeserta());

        setVisible(true);
    }

    /* ================= LOGIKA ================= */
    private void tambahPeserta() {

        if (daftarPeserta.isEmpty()) {
            paketDipilih = comboPaket.getSelectedItem().toString();
            comboPaket.setEnabled(false);
        }

        String nama = txtNama.getText();
        String hp = txtHp.getText();
        String alamat = txtAlamat.getText();

        if (nama.isEmpty() || hp.isEmpty() || alamat.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
            return;
        }

        if (!hp.matches("\\d{10,13}")) {
            JOptionPane.showMessageDialog(this, "Nomor HP harus 10–13 digit!");
            return;
        }

        String data =
                "Nama   : " + nama +
                        "\nHP     : " + hp +
                        "\nAlamat : " + alamat;

        daftarPeserta.add(data);

        JOptionPane.showMessageDialog(this, "Peserta berhasil ditambahkan");

        txtNama.setText("");
        txtHp.setText("");
        txtAlamat.setText("");
    }

    private void tampilkanPeserta() {
        areaOutput.setText("");

        if (daftarPeserta.isEmpty()) {
            areaOutput.setText("Belum ada peserta.");
            return;
        }

        areaOutput.append("Paket Tour: " + paketDipilih + "\n\n");

        int no = 1;
        for (String p : daftarPeserta) {
            areaOutput.append(no++ + ". " + p + "\n\n");
        }
    }

    private void hapusPeserta() {
        if (daftarPeserta.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Belum ada peserta.");
            return;
        }

        String input = JOptionPane.showInputDialog(this, "Masukkan nomor peserta:");

        if (input == null) return;

        try {
            int nomor = Integer.parseInt(input);

            if (nomor < 1 || nomor > daftarPeserta.size()) {
                JOptionPane.showMessageDialog(this, "Nomor tidak valid.");
                return;
            }

            daftarPeserta.remove(nomor - 1);
            JOptionPane.showMessageDialog(this, "Peserta berhasil dihapus.");

            if (daftarPeserta.isEmpty()) {
                comboPaket.setEnabled(true);
                paketDipilih = "";
            }

            tampilkanPeserta();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Input harus angka.");
        }
    }

    /* ================= MAIN ================= */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainApp());
    }
}
