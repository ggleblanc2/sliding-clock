# Java Swing Sliding Clock

## Introduction

If you’re not familiar with Java Swing, Oracle has an excellent tutorial to get you started, [Creating a GUI With JFC/Swing](https://docs.oracle.com/javase/tutorial/uiswing/index.html). Skip the Netbeans section.

I enjoy making diffrerent clocks using Java Swing.  This is one of those examples.

Here's what the GUI looks like.

![GUI](2020-12-08.png)

I caught the clock as the seconds were changing from 51 to 52.  The digits rotate upward.

I used the [model / view / controller](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) pattern when creating this GUI.  So, let's talk about the model, view, and controller.

## Model

In the `SlidingClockModel` class, I get the current time from a `Calendar` instance.  (This is old code.)  I convert the current time to a `String` and substring the hours, minutes, seconds, and AM/PM from the time `String`.

## View

The application starts with a call to the  `SwingUtilities` `invokeLater` method.  This ensures that the Swing components are created and updated on the [Event Dispatch Thread](https://docs.oracle.com/javase/tutorial/uiswing/concurrency/dispatch.html).

The view consists of an undecorated `JFrame` which contains one clock `JPanel`.  The clock `JPanel` is made up of six sliding `JPanels`, one for the hours, one for the tens minute, one for the ones minute, one for the tens second, one for the ones second, and one for the AM/PM indicator.

A sliding `JPanel` is created with a `String` array and a `Font`.  The `String` array is converted into a `BufferedImage` of the `String` array elements arranged in a column.  The first element of the `String` array is duplicated at the bottom of the `BufferedImage` strip.  This allows the strip to continually move upward.  When the strip reaches the bottom, it's transposed to the top of the strip.  Since the first and last `String` of the strip are the same, you don't see the adjustment.

When the value of the sliding `JPanel` changes, the `BufferedImage` strip is moved upward by an inner `Runnable` running in a `Thread`.  The motion happens in 10 steps of 30 milliseconds each.

## Controller

Since there are no controls on this clock, the controller is a `Runnable` that runs in a `Thread`.  Every 200 milliseconds, the controller sets the current time in the model and tells the view to update based on the time in the model.  The view update is placed in a `SwingUtilities` `invokeLater` runnable to ensure tha the Swing component updates happen on the [Event Dispatch Thread](https://docs.oracle.com/javase/tutorial/uiswing/concurrency/dispatch.html).
