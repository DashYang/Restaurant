package restaurant.UI;

import java.util.LinkedList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import restaurant.Iterator.ButtonLinkCommandIterator;
import restaurant.Iterator.Iterator;
import restaurant.factory.Storage;
import restaurant.food.ingredient.GourmetPowder;
import restaurant.food.ingredient.Ingredient;
import restaurant.food.ingredient.Oil;
// import restaurant.food.ingredient.Pepper;
import restaurant.food.ingredient.Salt;
import restaurant.food.ingredient.Sauce;
import restaurant.food.ingredient.Vinegar;
import restaurant.food.material.Chicken;
import restaurant.food.material.Eggplant;
import restaurant.food.material.Material;
import restaurant.food.material.Meat;
import restaurant.food.material.Potato;
import restaurant.food.material.Tomato;
import restaurant.kitchen.command.AddIngredientCommand;
import restaurant.kitchen.command.AddMaterialCommand;
import restaurant.kitchen.command.ButtonLinkCommand;
import restaurant.menu.Observer;
import restaurant.state.Restaurant;

public class Uiswt implements Observer {

	private volatile static Uiswt			uniqueInstance;
	protected Shell							shell;
	protected Display						display;
	private Canvas							canvas;
	private Composite						leftComposite, rightComposite;

	private Storage							storage;						//库存
	private Button							beginBussinessButton;			//营业按钮
	private LinkedList<ButtonLinkCommand>	buttonLinkCommands;			//按钮与命令的集合
	private LinkedList<NameAndLabel>		nameAndLabels;					//label 和 name 个集合

	protected int							seats, customers;				//座子，顾客
	protected Boolean[]						seatsList;						//座位
	protected boolean						isBusiness;					//是否与营业
	private Label							moneyLabel, customerLabel;		//营业额顾客数
	private Label[]							eatingLabels;

	private Label[]							rollingLabels;

	private Restaurant						restaurant;

	private Uiswt() {
		seats = 15;		//有15个凳子，最多有15个顾客
		customers = 0;
		isBusiness = false;
		buttonLinkCommands = new LinkedList<ButtonLinkCommand>();
		nameAndLabels = new LinkedList<NameAndLabel>();
		seatsList = new Boolean[15];
		eatingLabels = new Label[15];
		for (int i = 0; i < seatsList.length; i++) {
			seatsList[i] = true;
		}
		this.storage = Storage.getInstance();
		storage.registerObserver(this);
		restaurant = Restaurant.getInstance();
	}

	public static Uiswt getInstance() {
		if (uniqueInstance == null) {
			synchronized (Uiswt.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new Uiswt();
				}
			}
		}
		return uniqueInstance;
	}

	public void swt() throws SWTException {
		display = new Display();
		shell = new Shell(display);
		createContents();
		addListener();
		update();

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	//整体布局
	public void buildMainShell() {
		shell.setSize(800, 600);
		shell.setText("Restaurant");
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		shell.setLayout(gridLayout);
		leftComposite = new Composite(shell, SWT.NONE);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 240;
		leftComposite.setLayoutData(gd);
		rightComposite = new Composite(shell, SWT.NONE);
		gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 560;
		rightComposite.setLayoutData(gd);
	}

	//右面布局
	public void buildrightomposite()
	{
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		gridLayout.marginWidth = gridLayout.marginHeight = 0;
		rightComposite.setLayout(gridLayout);
		Composite titleComposite = new Composite(rightComposite, SWT.NONE);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 120;
		titleComposite.setLayoutData(gd);
		Image titleImage = new Image(display, "image/title.jpg");
		titleComposite.setBackgroundImage(titleImage);

		Composite customerComposite = new Composite(rightComposite, SWT.BORDER);
		gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 360;
		customerComposite.setLayoutData(gd);
		customerComposite.setLayout(new FormLayout());

		Composite messageComposite = new Composite(rightComposite, SWT.NONE);
		gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 120;
		messageComposite.setLayoutData(gd);
		gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		gridLayout.marginWidth = gridLayout.marginHeight = 0;
		messageComposite.setLayout(gridLayout);

		Composite staticMessage = new Composite(messageComposite, SWT.BORDER);
		gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 120;
		staticMessage.setLayoutData(gd);
		gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		gridLayout.marginHeight = gridLayout.marginWidth = 0;
		staticMessage.setLayout(gridLayout);
		moneyLabel = new Label(staticMessage, SWT.NONE);
		gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 30;
		moneyLabel.setLayoutData(gd);
		customerLabel = new Label(staticMessage, SWT.NONE);
		gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 70;
		customerLabel.setLayoutData(gd);

		customerComposite.setLayout(new FormLayout());
		FormData data = new FormData();
		data.top = new FormAttachment(6);
		data.left = new FormAttachment(4);
		data.bottom = new FormAttachment(94);
		data.right = new FormAttachment(97);
		Composite customerBox = new Composite(customerComposite, SWT.BORDER);
		customerBox.setLayoutData(data);
		Image bkImage = new Image(display, "image/dengzi.jpg");
		customerBox.setBackgroundImage(bkImage);

		gridLayout = new GridLayout();
		gridLayout.numColumns = 5;
		gridLayout.marginWidth = gridLayout.marginHeight = 0;
		customerBox.setLayout(gridLayout);
		//System.out.println(seatsList.length);
		//System.out.println(eatingLabels.length);
		for (int i = 0; i < eatingLabels.length; i++) {

			eatingLabels[i] = new Label(customerBox, SWT.NONE);
			gd = new GridData(GridData.FILL_BOTH);
			Image eatingImage = new Image(display, "image/eating.jpg");
			eatingLabels[i].setBackgroundImage(eatingImage);
			eatingLabels[i].setLayoutData(gd);
			eatingLabels[i].setVisible(false);
		}


		rollingLabels = new Label[4];
		Composite rollingMessage = new Composite(messageComposite, SWT.NONE);
		gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 420;
		rollingMessage.setLayoutData(gd);
		gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		gridLayout.marginHeight = gridLayout.marginWidth = 0;
		rollingMessage.setLayout(gridLayout);

		for (int i = 0; i < 4; i++) {
			gd = new GridData(GridData.FILL_BOTH);
			gd.widthHint = 100;
			rollingLabels[i] = new Label(rollingMessage, SWT.BORDER);
			rollingLabels[i].setLayoutData(gd);
			//rollingLabels[i].setText("显示即时信息");
		}
	}





	//左面布局
	public void buildLeftComposite() {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		gridLayout.marginWidth = gridLayout.marginHeight = 0;
		leftComposite.setLayout(gridLayout);
		canvas = new Canvas(leftComposite, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 220;
		canvas.setLayoutData(gd);
		final Image image = new Image(display, "image/cook.jpg");
		canvas.setLayout(new FillLayout());
		canvas.addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				final Rectangle bounds = image.getBounds();
				int picwidth = bounds.width;
				int picheight = bounds.height;
				double xradio = 220.0 / picwidth;
				double yradio = 210.0 / picheight;
				e.gc.drawImage(image, 0, 0, picwidth, picheight, 0, 0, (int) (xradio
					* picwidth), (int) (yradio * picheight));
			}
		});

		beginBussinessButton = new Button(leftComposite, SWT.NONE);
		gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 40;
		beginBussinessButton.setText("开始营业");
		beginBussinessButton.setLayoutData(gd);

		Composite showStorage = new Composite(leftComposite, SWT.BORDER);
		gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 320;
		showStorage.setLayoutData(gd);

		GridLayout gl = new GridLayout(1, true);
		showStorage.setLayout(gl);

		//主料添加面板
		Group gp1 = new Group(showStorage, SWT.NONE);
		gp1.setText("主料剩余情况一览");
		gl = new GridLayout(2, true);
		gp1.setLayout(gl);
		gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 160;
		gp1.setLayoutData(gd);

		Material tm = new Meat();
		addOperation(gp1, tm);

		tm = new Potato();
		addOperation(gp1, tm);

		tm = new Eggplant();
		addOperation(gp1, tm);

		tm = new Tomato();
		addOperation(gp1, tm);

		tm = new Chicken();
		addOperation(gp1, tm);
		//佐料添加面板
		Group gp2 = new Group(showStorage, SWT.NONE);
		gp2.setText("佐料剩余情况一览");
		gp2.setLayout(gl);
		gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 160;
		gp2.setLayoutData(gd);

		Ingredient tmp = new Oil();
		addOperation(gp2, tmp);

		tmp = new Salt();
		addOperation(gp2, tmp);

		tmp = new Sauce();
		addOperation(gp2, tmp);

		tmp = new Vinegar();
		addOperation(gp2, tmp);

		tmp = new GourmetPowder();
		addOperation(gp2, tmp);
	}

	//创建面板
	public void createContents() {

		buildMainShell();
		buildLeftComposite();
		buildrightomposite();
	}

	/**
	 * 增加监听器
	 */
	private void addListener() {

		beginBussinessButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!isBusiness) {
					isBusiness = true;
					beginBussinessButton.setText("停止营业");

					DoBusiness doBusiness = new DoBusiness();
					new Thread(doBusiness).start();

					restaurant.open();
				}
				else {
					isBusiness = false;
					beginBussinessButton.setText("开始营业");
					restaurant.close();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		Iterator it = new ButtonLinkCommandIterator(buttonLinkCommands);
		while (it.hasNext())
		{
			final ButtonLinkCommand tmp = (ButtonLinkCommand) it.next();
			tmp.getBtn().addSelectionListener(new SelectionListener()
			{
				@Override
				public void widgetSelected(SelectionEvent e)
				{
					restaurant.buy(tmp.getCmd());
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0)
				{

				}
			});
		}
	}

	//添加增加佐料按钮
	public void addOperation(Group gp, Ingredient ingredient)
	{
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 20;
		Label label = new Label(gp, SWT.NONE);
		label.setText(ingredient.getChineseName() + "：" + storage.getAmount(ingredient));
		NameAndLabel nab = new NameAndLabel(label, ingredient.getChineseName(), ingredient.getName());
		nameAndLabels.add(nab);

		Button button = new Button(gp, SWT.NONE);
		button.setText("购入食材");
		button.setLayoutData(gd);
		ButtonLinkCommand blc = new ButtonLinkCommand(button, new AddIngredientCommand(ingredient));
		buttonLinkCommands.add(blc);
	}

	//添加增加主料按钮
	public void addOperation(Group gp, Material material)
	{
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 20;
		Label label = new Label(gp, SWT.NONE);
		label.setText(material.getChineseName() + "：" + storage.getAmount(material));
		NameAndLabel nab = new NameAndLabel(label, material.getChineseName(), material.getName());
		nameAndLabels.add(nab);

		Button button = new Button(gp, SWT.NONE);
		button.setText("购入食材");
		button.setLayoutData(gd);
		ButtonLinkCommand blc = new ButtonLinkCommand(button, new AddMaterialCommand(material));
		buttonLinkCommands.add(blc);
	}

	//弹提示框
	public void openMessageBox(final String message)
	{
		if (!display.isDisposed()) {
			Runnable runable = new Runnable() {
				@Override
				public void run() {
					int style = SWT.APPLICATION_MODAL;
					MessageBox messageBox = new MessageBox(shell, style);
					messageBox.setMessage(message);
					messageBox.open();
				}
			};
			display.syncExec(runable);
		}
	}

	public void takeSeat(int i) {
		seatsList[i] = false;
	}

	public void releaseSeat(int i) {
		seatsList[i] = true;
	}


	@Override
	public void update() {		//当食材的数量改变时，窗口的显示有可能改变

		try {
			if (!display.isDisposed()) {
				Runnable runnable = new Runnable() {
					@Override
					public void run() {
						
						try{
						moneyLabel.setText("当前营业额：" + Storage.getInstance().getRevenue());
						customerLabel.setText("当前顾客人数：" + customers);

						for (int i = 0; i < eatingLabels.length; i++) {
							if (!seatsList[i] && !eatingLabels[i].getVisible()) {
								eatingLabels[i].setVisible(true);
							}
							else if (seatsList[i] && eatingLabels[i].getVisible()) {
								eatingLabels[i].setVisible(false);
							}
						}

						for (NameAndLabel nal : nameAndLabels) {
							Label tmp = nal.getLabel();
							tmp.setText(nal.getChineseName() + "：" + storage.getAmount(nal.getName()));
						}
						}
						catch(Exception e){
							System.out.println("多半是由线程引起的");
						}
					}
				};
				display.syncExec(runnable);
			}
		}
		catch (SWTException e) {
			System.out.println("swt 的线程真让人伤心");
		}
	}
	
	public void updateRollingLabel(){
		try {
			if (!display.isDisposed()) {
				Runnable runnable = new Runnable() {
					@Override
					public void run() {
						MessageQueue messageQueue = MessageQueue.getInstance();
						for(int i=0;i<4;i++){
							rollingLabels[i].setText(messageQueue.getMessage(i));
						}
					}
				};
				display.syncExec(runnable);
			}
		}
		catch (SWTException e) {
			System.out.println("swt 的线程真让人伤心");
		}
	}
	
}
