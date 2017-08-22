# Pre-work
Pre work simple to do app for code path application. 

# Pre-work - *Simple todo app*

**Simple todo app** is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item.

Submitted by: **Rebecca He Zhang**

Time spent: **8** hours spent in total

## User Stories

The following **required** functionality is completed:

* [ ] User can **successfully add and remove items** from the todo list
* [ ] User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list.
* [ ] User can **persist todo items** and retrieve them properly on app restart

The following **optional** features are implemented:

* [ ] Persist the todo items [into SQLite](http://guides.codepath.com/android/Persisting-Data-to-the-Device#sqlite) instead of a text file
* [ ] Improve style of the todo items in the list [using a custom adapter](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView)
* [ ] Add support for completion due dates for todo items (and display within listview item)
* [ ] Use a [DialogFragment](http://guides.codepath.com/android/Using-DialogFragment) instead of new Activity for editing items
* [ ] Add support for selecting the priority of each todo item (and display in listview item)
* [ ] Tweak the style improving the UI / UX, play with colors, images or backgrounds

The following **additional** features are implemented:

* [ ] List anything else that you can get done to improve the app functionality!

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='http://imgur.com/4e49adbd-bb5b-492a-b293-475699d2480f' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Project Analysis

As part of your pre-work submission, please reflect on the app and answer the following questions below:

**Question 1:** "What are your reactions to the Android app development platform so far? Compare and contrast Android's approach to layouts and user interfaces in past platforms you've used."

**Answer:** [
I like Android studio. First the UI is clean and easy to use, especially there are some built-in automatic spelling check, sysntax check, auto-filling functions. They made the development more effecient and decrease the rate of making errors. 
Plus, the view and model are seperated, which draw a line between layout design and models/data/logics behind implementation. In this way, people can focus on one side at one time, to be more concentraded and professional. This is better than NetBeans that I have used before for an old desktop application development, which use java swing library (code) to implement everything, including GUI. ].

**Question 2:** "Take a moment to reflect on the `ArrayAdapter` used in your pre-work. How would you describe an adapter in this context and what is its function in Android? Why do you think the adapter is important? Explain the purpose of the `convertView` in the `getView` method of the `ArrayAdapter`."

**Answer:** [
An adapter object is like a bridge linking the data and a view together. It allows access to data items and also able to make a view for each item. Here the adapter connects the todo list items and the ListView. It's important because it reflects the changes of the data and show it through ListView after user adding, deleting, or editing the content of the items.  

The purpose of the 'converView' is to increase the performance of the Adapter by recycling old view obejects that are no longer being used.
].

## Notes

Describe any challenges encountered while building the app.

There were two or three times the app just stopped working but without any buidling issues. 
It was very hard to tell wherw went wrong directly, but after setting up a few break points and kept monitoring the logcat, I found out at which line it crashes, and the failure type. Based on this clue, I was able to search and thought about the solutions.

## License

    Copyright [yyyy] [name of copyright owner]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
