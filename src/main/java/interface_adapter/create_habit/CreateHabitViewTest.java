package interface_adapter.create_habit;

import use_case.create_habit.CreateHabitInputBoundary;
import use_case.create_habit.CreateHabitInputData;

import javax.swing.*;

/**
 * 简单的测试入口，用于在一个 JFrame 中展示 CreateHabitView。
 * 依赖：
 *  - CreateHabitView
 *  - CreateHabitController
 *  - CreateHabitViewModel
 *  - CreateHabitState
 *  - use_case.create_habit.CreateHabitInputBoundary
 *  - use_case.create_habit.CreateHabitInputData
 */
public class CreateHabitViewTest {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 1. 准备 ViewModel
            CreateHabitViewModel viewModel = new CreateHabitViewModel("create-habit-view");

            // 2. 准备一个假的 Interactor（只做打印 + 更新 ViewModel，不连数据库）
            CreateHabitInputBoundary dummyInteractor = new DummyCreateHabitInteractor(viewModel);

            // 3. 准备 Controller
            CreateHabitController controller = new CreateHabitController(dummyInteractor);

            // 4. 准备 View（这里用户名随便写一个）
            String username = "testUser";
            interface_adapter.create_habit.CreateHabitView view = new interface_adapter.create_habit.CreateHabitView(controller, viewModel, username);

            // 5. 放进 JFrame 里显示
            JFrame frame = new JFrame("CreateHabitView Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(view);
            frame.pack();
            frame.setLocationRelativeTo(null); // 居中
            frame.setVisible(true);
        });
    }

    /**
     * 一个简单的假的 Interactor，只用于测试 UI：
     * - 打印收到的输入
     * - 更新 ViewModel 的 state 显示“模拟创建成功”。
     */
    private static class DummyCreateHabitInteractor implements CreateHabitInputBoundary {

        private final CreateHabitViewModel viewModel;

        public DummyCreateHabitInteractor(CreateHabitViewModel viewModel) {
            this.viewModel = viewModel;
        }

        @Override
        public void execute(CreateHabitInputData inputData) {
            // 控制台打印一下，方便你看是不是参数传对了
            System.out.println("DummyCreateHabitInteractor.execute 被调用:");
            System.out.println("  username = " + inputData.getUsername());
            System.out.println("  habitName = " + inputData.getHabitName());
            System.out.println("  startDateTime = " + inputData.getStartDateTime());
            System.out.println("  frequency = " + inputData.getFrequency());
            System.out.println("  habitGroup = " + inputData.getHabitGroup());
            System.out.println("  streakCount = " + inputData.getStreakCount());
            System.out.println("  priority = " + inputData.getPriority());

            // 模拟“创建成功”：更新 ViewModel 的 state
            CreateHabitState state = viewModel.getState();
            state.setSuccessMessage("模拟创建成功: " + inputData.getHabitName());
            state.setErrorMessage(null);

            // 也可以顺便清空表单字段
            state.setHabitName("");
            state.setStartDateTimeText("");
            state.setFrequencyText("");
            state.setHabitGroup("");
            state.setStreakCount(0);
            state.setPriority(0);

            viewModel.setState(state);
            viewModel.firePropertyChanged();
        }
    }
}
