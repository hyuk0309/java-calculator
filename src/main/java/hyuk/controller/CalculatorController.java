package hyuk.controller;

import hyuk.model.LogDTO;
import hyuk.model.ResultDTO;
import hyuk.service.CalculatorService;
import hyuk.view.InputView;
import hyuk.view.OutputView;
import java.util.Scanner;

public class CalculatorController {

    private final InputView inputView;
    private final OutputView outputView;
    private final CalculatorService calculatorService;

    public CalculatorController(InputView inputView, OutputView outputView,
        CalculatorService calculatorService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.calculatorService = calculatorService;
    }

    public void run() {
        while (true) {
            outputView.printMenu();
            try {
                String menu = inputView.selectMenu(new Scanner(System.in));
                outputView.printEmptySpace();

                if (menu.equals("1")) {
                    LogDTO logDTO = calculatorService.printLogs();
                    outputView.printLogs(logDTO);
                    continue;
                }
                calculate();
            } catch (Exception e) {
                System.out.println(e.getMessage() + "\n");
                continue;
            }
        }
    }

    private void calculate() {
        while (true) {
            try {
                String formula = inputView.inputFormula(new Scanner(System.in));

                ResultDTO resultDTO = calculatorService.calculate(formula);

                outputView.printResult(resultDTO);
                return;
            } catch (Exception e) {
                System.out.println(e.getMessage() + "\n");
            }
        }
    }

}