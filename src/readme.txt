ingredient 作为配料的包，包含了辣椒，食盐，葱，蒜等类
material 作为原料的包，包含了肉，豆类，青菜等类,相比于ingredient，它多出了一个叫amount(分量)的属性

dish 作为每一道菜的包，采用了策略模式，将容易变化的部分：配料和原料分开，与书中不同的是，我们表示配料原料没有用接口，而是使用了超类的数组。

菜单和食材储备之间用了观察者模式。当食材是数量改变，当前菜单有可能发生改变，只有菜单上有的菜才能进入点菜的队列。