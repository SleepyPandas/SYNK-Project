package test;

import interface_adapter.create_habit.CreateHabitController;
import interface_adapter.create_habit.CreateHabitViewModel;
import use_case.create_habit.CreateHabitInputBoundary;
import use_case.create_habit.CreateHabitInteractor;
import use_case.create_habit.CreateHabitOutputBoundary;
import use_case.create_habit.CreateHabitUserDataAccess;

public class CreateHabitDemo {

    public static void main(String[] args) {
        // 1. 准备 ViewModel
        CreateHabitViewModel viewModel = new CreateHabitViewModel("test");

        // （可选）如果你的 ViewModel 支持监听，可以加上：
        // viewModel.addPropertyChangeListener(evt -> {
        //     System.out.println("[PropertyChange] " + evt.getPropertyName());
        // });

        // 2. 准备 DAO + Presenter + Interactor + Controller
        CreateHabitUserDataAccess dao = new InMemoryHabitDataAccess();
        CreateHabitOutputBoundary presenter = new ConsoleCreateHabitPresenter(viewModel);
        CreateHabitInputBoundary interactor = new CreateHabitInteractor(dao, presenter);
        CreateHabitController controller = new CreateHabitController(interactor);

        String username = "roy";

        // ---------- 测试 1：正常创建 ----------
        System.out.println(">>> Test 1: 正常创建");
        controller.execute(
                username,
                "Read book",
                "2025-11-23T09:00", // 要符合 LocalDateTime.parse 的格式
                1,
                "DAY",
                "Study",
                0,
                1
        );

        // ---------- 测试 2：重复名称（应该走 fail view） ----------
        System.out.println(">>> Test 2: 重复名称");
        controller.execute(
                username,
                "Read book",        // 和上面同名，DAO 会判定重复
                "2025-11-23T10:00",
                1,
                "DAY",
                "Study",
                0,
                1
        );

        // ---------- 测试 3：非法时间格式（Controller 里会抛异常） ----------
        System.out.println(">>> Test 3: 非法时间格式");
        try {
            controller.execute(
                    username,
                    "Bad date habit",
                    "not-a-datetime",   // 故意错的
                    1,
                    "DAY",
                    "Study",
                    0,
                    1
            );
        } catch (IllegalArgumentException e) {
            System.out.println("[Controller] Caught IllegalArgumentException: " + e.getMessage());
        }

        System.out.println("Demo 完成。");
    }
}
