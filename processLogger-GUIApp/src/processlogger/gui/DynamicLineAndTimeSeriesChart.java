package processlogger.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class DynamicLineAndTimeSeriesChart extends ApplicationFrame implements ActionListener {

    /**
     * The time series data.
     */
    private TimeSeries series;
    /**
     * The most recent value added.
     */
    private double lastValue = 100.0;
    /**
     * Timer to refresh graph after every 1/4th of a second
     */
    private Timer timer = new Timer(10, this);

    /**
     * Constructs a new dynamic chart application.
     *
     * @param title the frame title.
     */
    public DynamicLineAndTimeSeriesChart(final String title) {

        super(title);
        this.series = new TimeSeries("CPU vs Time", Millisecond.class);

        //JDBCCategoryDataset dataset= new JDBCCategoryDataset(title, title, title, title)
        final TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);
        final JFreeChart chart = createChart(dataset);

        timer.setInitialDelay(1000);

        //Sets background color of chart
        chart.setBackgroundPaint(Color.LIGHT_GRAY);

        //Created JPanel to show graph on screen
        final JPanel content = new JPanel(new BorderLayout());


        //Created Chartpanel for chart area
        final ChartPanel chartPanel = new ChartPanel(chart);

        //Added chartpanel to main panel
        content.add(chartPanel);

        //Sets the size of whole window (JPanel)
        // chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
        chartPanel.setPreferredSize(new java.awt.Dimension(1000, 500));
        //Puts the whole content on a Frame
        setContentPane(content);

        timer.start();

    }

    /**
     * Creates a sample chart.
     *
     * @param dataset the dataset.
     *
     * @return A sample chart.
     */
    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
                "CPU vs Time",
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
        yaxis.setRange(0.000000, 50.000);

        return result;
    }

    /**
     * Generates an random entry for a particular call made by time for every
     * 1/4th of a second.
     *
     * @param e the action event.
     */
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
            i = Double.parseDouble(str[0]);
        } catch (Exception ex) {
            //Logger.getLogger(DynamicLineAndTimeSeriesChart.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            log_gen.clientsocket2.close();
            this.setVisible(false);
            this.dispose();
        }

        // final double factor = 0.9 + 0.2*Math.random();
        //final double factor=100;
        //this.lastValue = this.lastValue * factor;
        this.lastValue = i;
        // i++;
        final Millisecond now = new Millisecond();
        this.series.add(new Millisecond(), this.lastValue);

        System.out.println("Current Time in Milliseconds = " + now.toString() + ", Current Value : " + this.lastValue);

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
//            //    ia = InetAddress.getByName(host.getText());
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }

        try {
            log_gen.clientsocket2 = new DatagramSocket(cport);
            //log_gen.clientsocket.send(new DatagramPacket(buf, graph.length(), ia, sport));
        } catch (Exception e) {
            System.out.println(e);
        }

        final DynamicLineAndTimeSeriesChart demo = new DynamicLineAndTimeSeriesChart("CPU vs Time");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
        ApplicationFrame frame = new ApplicationFrame("Graph");
        frame.setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

//        frame.dispose();
//        frame.setVisible(false);


        // frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSED));
        //frame.hide(new WindowEvent(frame, WindowEvent.WINDOW_CLOSED));
        //  frame.windowClosed(WindowEvent.WINDOW_LOST_FOCUS);
    }
//    public void Destroy(){
//        
//    }
    //public static byte buf[] = new byte[1024];
    private static int cport = 6002;
    private static byte buf1[] = new byte[1024];
    private static DatagramPacket dp = new DatagramPacket(buf1, buf1.length);
    String val;
    double i;
    //private static DatagramSocket clientsocket2;
}
