/*
A method of retrieving controllers when they are connected while the game is currently running
 */
private static ControllerEnvironment createDefaultEnvironment() throws ReflectiveOperationException {
    Constructor<ControllerEnvironment> constructor = (Constructor<ControllerEnvironment>)
            Class.forName("net.java.games.input.DefaultControllerEnvironment").getDeclaredConstructors()[0];

    constructor.setAccessible(true);

    return constructor.newInstance();

    if (Assets.INPUT.inputReleased(Keys.F4)) {
        try {
            Controllers.getControllers().clear();
            for (final net.java.games.input.Controller c : createDefaultEnvironment().getControllers()) {
                for (String controllerName : Assets.CONTROLLER_NAMES) {
                    if (c.getName().toLowerCase().contains(controllerName.toLowerCase())) {
                        for (Component cc : c.getComponents()) {
                            System.out.println(cc.getName());
                        }
                        Controllers.getControllers().add(new Controller() {
                            @Override
                            public boolean getButton(int buttonCode) {
                                return false;
                            }

                            @Override
                            public float getAxis(int axisCode) {
                                return 0;
                            }

                            @Override
                            public PovDirection getPov(int povCode) {
                                return null;
                            }

                            @Override
                            public boolean getSliderX(int sliderCode) {
                                return false;
                            }

                            @Override
                            public boolean getSliderY(int sliderCode) {
                                return false;
                            }

                            @Override
                            public Vector3 getAccelerometer(int accelerometerCode) {
                                return null;
                            }

                            @Override
                            public void setAccelerometerSensitivity(float sensitivity) {
                            }

                            @Override
                            public String getName() {
                                return c.getName();
                            }

                            @Override
                            public void addListener(ControllerListener listener) {
                            }

                            @Override
                            public void removeListener(ControllerListener listener) {
                            }
                        });
                    }
                }
            }
            Controllers.removeListener(Assets.INPUT.getGamepad());
            Controllers.addListener(Assets.INPUT.getGamepad());
        } catch (Exception ignored) {}
    }
}