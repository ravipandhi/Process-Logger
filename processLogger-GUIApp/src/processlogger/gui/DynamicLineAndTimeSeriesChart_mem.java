package processlogger.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.StringTokenizer;

import javax.swing.JPanel;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * An example to show how we can create a dynamic chart.
 */
public class DynamicLineAndTimeSeriesChart_mem extends ApplicationFrame implements ActionListener {

    /**
     * The time series data.
     */
    private TimeSeries series;
    /**
     * The most recent value added.
     */
    private double lastValue = 100.0;
    private Timer timer = new Timer(10, this);

    /**
     * Constructs a new dynamic chart application.
     *
     * @param title the frame title.
     */
    public DynamicLineAndTimeSeriesChart_mem(final String title) {

        super(title);
        this.series = new TimeSeries("CPU vs Time", Millisecond.class);

        //JDBCCategoryDataset dataset= new JDBCCategoryDataset(title, title, title, title)
        final TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);
        final JFreeChart chart = createChart(dataset);

        timer.setInitialDelay(1000);


        chart.setBackgroundPaint(Color.LIGHT_GRAY);

        final JPanel content = new JPanel(new BorderLayout());

        final ChartPanel chartPanel = new ChartPanel(chart);

        content.add(chartPanel);


        chartPanel.setPreferredSize(new java.awt.Dimension(1000, 500));

        setContentPane(content);

        timer.start();

    }

    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
                "Memory vs Time",
                "Time",
                "Value",
                dataset,
                true,
                true,
                false);

        final XYPlot plot = result.getXYPlot();

        plot.setBackgroundPaint(new Color(0xffffe0));
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.lightGray);

        ValueAxis xaxis = plot.getDomainAxis();
        xaxis.setAutoRange(true);

        //Domain axis would show data of 60 seconds for a time
        xaxis.setFixedAutoRange(60000.0);  // 60 seconds
        xaxis.setVerticalTickLabels(true);

        ValueAxis yaxis = plot.getRangeAxis();
        yaxis.setRange(0.000000, 50.0000);

        return result;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        try {
            System.out.println("in action");
            log_gen.clientsocket2.setSoTimeout(1000);
            log_gen.clientsocket2.receive(dp);
            System.out.println("dp rec");
            val = new String(dp.getData(), 0, dp.getLength());
            String str[] = new String[2];
            int j = 0;
            StringTokenizer strt = new StringTokenizer(val, "=;");
            while (strt.hasMoreTokens()) {
                str[j] = strt.nextToken();
                System.out.println("Data is " + str[j]);
                j++;
            }

            System.out.println(val);
            i = Double.parseDouble(str[1]);
        } catch (Exception ex) {
            System.out.println(ex);
            log_gen.clientsocket2.close();
            this.setVisible(false);
            this.dispose();
        }


        this.lastValue = i;
        // i++;
        final Millisecond now = new Millisecond();
        this.series.add(new Millisecond(), this.lastValue);

        System.out.println("Current Time in Milliseconds = " + now.toString() + ", Current Value : " + this.lastValue);
    }

    /**
     * Starting point for the dynamic graph application.
     *
     * @param args ignored.
     */
    public static void main(final String[] args) throws IOException {

//        String graph = "graph";
//        buf = graph.getBytes();
//        byte IP[] = new byte[]{(byte) 25, (byte) 153, (byte) 25, (byte) 152};
//        try {
//            ia = InetAddress.getByAddress(IP);
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }

        try {
            log_gen.clientsocket2 = new DatagramSocket(cport);
            //clientsocket3.send(new DatagramPacket(buf, graph.length(), ia, sport));
        } catch (Exception e) {
            System.out.println(e);
        }

        final DynamicLineAndTimeSeriesChart_mem demo = new DynamicLineAndTimeSeriesChart_mem("Memory vs Time");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
        ApplicationFrame frame = new ApplicationFrame("Graph");
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSED));
    }
    //public static byte buf[] = new byte[1024];
    private static int cport = 6002;
    private static byte buf1[] = new byte[1024];
    private static DatagramPacket dp = new DatagramPacket(buf1, buf1.length);
    String val;
    double i;
    //private static DatagramSocket clientsocket3;
}
