import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.scene.shape.Circle; 
import javafx.scene.shape.Rectangle; 
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.shape.Line;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent; 
import javafx.scene.input.ScrollEvent;
import javafx.scene.control.ScrollBar;
public class OS141 extends Application{
    private static int NUM_USERS = 4;
    private static int NUM_DISKS = 2;
    private static int NUM_PRINTERS = 3;
	public static String [] userFileNames = new String[NUM_USERS];
	public static UserThread[] users = new UserThread[NUM_USERS];
	public static Disk[] disks = new Disk[NUM_DISKS];
	public static Printer[] printers = new Printer[NUM_PRINTERS];
	public static DiskManager diskManager = new DiskManager(NUM_DISKS);
	public static PrinterManager printerManager = new PrinterManager(NUM_PRINTERS);
	static{
        for(int i = 0; i < NUM_DISKS; i++)
        {
                disks[i] = new Disk();
        }
        for(int i = 0; i < NUM_PRINTERS; i++)
        {
                printers[i] = new Printer(i+1);
            System.out.println("PRINTER" + (i+1));
        }

        for(int i = 0; i < NUM_USERS; i++)
        {
            userFileNames[i] = "USER" + (i+1);
            users[i] = new UserThread(i+1);
        }
    }
    public static Circle[] circlesU = new Circle[NUM_USERS];
    public static Rectangle[] rectP = new Rectangle[NUM_USERS];
    public static Rectangle[] rectD = new Rectangle[NUM_USERS];
    public static Rectangle[] PtrRect = new Rectangle[NUM_PRINTERS];
    public static Rectangle[] DiskRect = new Rectangle[NUM_DISKS];
    public static Text[] pTitles = new Text[NUM_USERS];
    public static Text[] dTitles = new Text[NUM_USERS];
    public static Text[] uNames = new Text[NUM_USERS];
    public static Line[][] UserToPtr = new Line[NUM_USERS][NUM_PRINTERS];
    public static Line[][] UserToDisk = new Line[NUM_USERS][NUM_DISKS];
    public static Text[] DiskData = new Text[NUM_DISKS];
    public static Text[] PrinterData  = new Text[NUM_PRINTERS];
    public static Text speedF = new Text(460,10,"faster");
    public static Text speedS = new Text(600,10,"slower");
    public static ScrollBar Speed = new ScrollBar();
    public static Text Name = new Text(10,10,"Henry Feng; 79695021");
	@Override
    public void start(Stage stage) {
        Scene scene;
    	stage.setTitle("Drawing Operations Test");
        int recWidth = 500;
        int recLength = 300;
        int radius = 20;
        int Xgap = 200; 
        int Ygap = 20;
    	Group root = new Group();

        for(int i = 0; i < NUM_PRINTERS; i++)
        {
            PtrRect[i] = new Rectangle(3*Xgap, (recLength + 10)*(i) + Ygap, recWidth, recLength );
            PrinterData[i] = new Text(3*Xgap, (recLength + 10)*(i) + Ygap,("PRINTER" + (i+1) + "\n"));
            PrinterData[i].setTextOrigin(VPos.TOP);
            PrinterData[i].setStroke(Color.WHITE);
            root.getChildren().addAll(PtrRect[i], PrinterData[i]);
        }
        for(int i = 0; i < NUM_DISKS; i++)
        {
            DiskRect[i] = new Rectangle(0.5*Xgap, (1.5*recLength + 10)*i + Ygap,  recWidth/2, 1.5*recLength);
            DiskData[i] = new Text(0.5*Xgap,(1.5*recLength + 10)*(i) + Ygap,("DISK" + (i+1) + "\n")); 
            DiskData[i].setTextOrigin(VPos.TOP);
            DiskData[i].setStroke(Color.WHITE);
            root.getChildren().addAll(DiskRect[i], DiskData[i]);
        }
        for(int i = 0; i < NUM_USERS; i++)
        {
            circlesU[i] = new Circle((3*Xgap - 0.5*Xgap - recWidth/2)/2 + 0.5*Xgap + recWidth/2, Ygap*(5*i+1) + 200, radius, Color.BLACK);
            uNames[i] = new Text((3*Xgap - 0.5*Xgap - recWidth/2)/2 + 0.5*Xgap + recWidth/2 - 20 ,  Ygap*(5*i+1) + 200 - 10,("USER " + (i+1)));
            uNames[i].setTextOrigin(VPos.TOP);
            uNames[i].setStroke(Color.WHITE);
            for(int j = 0; j < NUM_DISKS; j++)
            {
                UserToDisk[i][j] = new Line(circlesU[i].getCenterX(), circlesU[i].getCenterY(),DiskRect[j].getX() + recWidth/2,DiskRect[j].getY() + recLength/2);
                root.getChildren().addAll(UserToDisk[i][j]);
            }
            for(int j = 0; j < NUM_PRINTERS; j++)
            {
                UserToPtr[i][j] = new Line(circlesU[i].getCenterX(), circlesU[i].getCenterY(),PtrRect[j].getX(),PtrRect[j].getY() + recLength/2);
                root.getChildren().addAll(UserToPtr[i][j]);
            }
            root.getChildren().addAll(circlesU[i],uNames[i]);
        }
        Button btn = new Button("Start!");
        btn.setLayoutX(400);
        btn.setLayoutY(20);
        btn.setOnMouseClicked((new EventHandler<MouseEvent>() { 
         public void handle(MouseEvent event) { 
                for(int i = 0; i < users.length; i++) 
                {
                    users[i].start();
                }
         }
        }));
        root.getChildren().addAll( btn, speedF,speedS, Speed,Name);
        scene = new Scene(root, 1000, 750);
        Speed.addEventFilter(MouseEvent.MOUSE_PRESSED,new EventHandler<MouseEvent>() {
             public synchronized void handle(MouseEvent event) {
                    System.out.println("Clicked");
                
                    for(int i = 0; i < NUM_USERS; i++) 
                    {
                        try{
                            
                            users[i].wait();

                        }catch(Exception e){
                        }
                    }
                    for(int i = 0; i < NUM_PRINTERS; i++) 
                    {
                        try{
                            printers[i].wait();
                        }catch(Exception e){
                        }
                    }
                    for(int i = 0; i < NUM_DISKS; i++)
                    {
                        try{
                        disks[i].wait();
                        }catch(Exception e){
                        }
                    }

            }

        }); 
  
        Speed.addEventFilter(MouseEvent.MOUSE_RELEASED,new EventHandler<MouseEvent>() {
             public synchronized void handle(MouseEvent event) { 
                    System.out.println("Release");
                try{
                    for(int i = 0; i < NUM_USERS; i++) 
                    {
                        users[i].notify();
                    }
                    for(int i = 0; i < NUM_PRINTERS; i++) 
                    {
                        printers[i].notify();
                    }
                    for(int i = 0; i < NUM_DISKS; i++)
                    {
                        disks[i].wait();
                    }
                }catch(Exception e){
                }
            }
        });
        Speed.setMax(2);
        Speed.setMin(0.1);
        Speed.getVisibleAmount();
        Speed.setPrefHeight(20);
        Speed.setLayoutX(scene.getWidth()-500);
        Speed.setValue(1);
        System.out.println("Speed: " + Speed.getValue());
        stage.setScene(scene);
        stage.show();
    }
	public static void main(String[] args) {
                
               
                if(args.length != 0 &&  args[args.length - 1].equals("-ng"))
                {
                    configure(args);
                    OS141 os  = new OS141();
                    for(int i = 0; i < os.users.length; i++) 
                           users[i].start();
                    }
                else
                { 
                    launch();
                }
    }


	static void configure(String[] argv)
    	{
        	NUM_USERS=Integer.parseInt(argv[0].substring(1));
            NUM_DISKS=Integer.parseInt(argv[Integer.parseInt(argv[0].substring(1)) + 1].substring(1));
            NUM_PRINTERS=Integer.parseInt(argv[Integer.parseInt(argv[0].substring(1)) + 2].substring(1));
    	}

}
