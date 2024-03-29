package me.liycxc.utils;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Liycxc
 */
public class ConsoleListener {
    HashMap<String, Action> answers = new HashMap<String, Action>();
    Scanner scanner;
    Action defaultAction;

    /**
     * Add an action for a message.
     * @param message A string timed. Ignore case. It has no inner space sequence of two spaces or more.
     * Example:"close connection"
     * @param action The method action.act() will be called when scanner get the message.
     */
    public void addAction(String message, Action action) {
        answers.put(message.toLowerCase(), action);
    }

    /**
     *
     * @param scanner Usually new Scanner(System.in).
     * Will not be closed after listening.
     * @param defaultAction The defaultAction.act() method will be called if an action is not added for a message.
     */
    public ConsoleListener(Scanner scanner, Action defaultAction) {
        this.scanner = scanner;
        this.defaultAction = defaultAction;

        if (scanner == null || defaultAction == null) {
            throw new NullPointerException("null params for ConsoleListener");
        }
    }

    public void removeAction(String message, Action action) {
        answers.remove(message, action);
    }

    public Action replaceAction(String message, Action action) {
        return answers.replace(message, action);
    }

    public void listenInNewThread() {
        Thread t = new Thread(() -> {
            try {
                listen();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        t.start();
    }

    /**
     * Use listenInNewThread() instead.
     * Listen to console input in current thread. It blocks the thread.
     */
    public void listen() throws InterruptedException {
        while (true) {
            String line = scanner.nextLine();
            String msg = line.replaceAll("[\\s]+", " ");
            msg = msg.trim().toLowerCase();
            Action action = answers.get(msg);
            if (action == null) {
                action = defaultAction;
            }
            action.act(line);
        }
    }

    public interface Action {
        void act(String msg) throws InterruptedException;
    }
}