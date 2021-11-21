"use strict";

//Get Canvas to display on
var g_canvas = document.getElementById("myCanvas");
var g_ctx = g_canvas.getContext("2d");
//Get Inputs to get user prefrences
var g_sessionLengthInput = document.getElementById("sessionLength");
var g_shortBreakLengthInput = document.getElementById("shortBreakLength");
var g_longBreakLengthInput = document.getElementById("longBreakLength");
var g_numSessionsInput = document.getElementById("numSessions");
//Important Globals
var g_intervalID;
var g_initialized = false;
var g_paused = false;
//Time Tracking
var g_startTime;
var g_currentTime;
var g_elapsedTime;
var g_currentSession = 1 //Starts at 1, not incremented when timer first started, counts breaks as sessions.
var g_currentSessionLength = NaN;
//Time Settings
var g_sessionLength = 0.5*60*1000; // In Milliseconds
var g_shortBreakLength = 0.10*60*1000; // In Milliseconds
var g_longBreakLength = 0.25*60*1000; // In Milliseconds
var g_numSessions = 4; //Num Sessions before long break

function startTimer() {
    let now = new Date();
    g_elapsedTime = 0;
    g_startTime = now.getTime();
    g_currentTime = now.getTime();
}

function pauseTimer() {
    if(g_paused) {
        return;
    }
    let now = new Date();
    g_currentTime = now.getTime();
    g_elapsedTime += g_currentTime - g_startTime;
    g_paused = true;
    window.clearInterval(g_intervalID);
}

function resumeTimer() {
    if(!g_paused) {
        return;
    }
    let now = new Date()
    g_startTime = now.getTime();
    g_currentTime = now.getTime();
    g_paused = false;
    g_intervalID = window.setInterval(update, 33.333);
}

function nextSession(playSound = true) {
    if(playSound) {
        document.getElementById('Doorbell').play();
    }
    g_startTime = NaN;
    g_currentTime = NaN;
    g_elapsedTime = NaN;
    g_initialized = false;
    g_paused = false;
    window.clearInterval(g_intervalID);
    g_currentSession++;
    return(updateCurrentSessionLength());
}

function updateCurrentSessionLength() {
    let sessionsTillLongBreak = 2 * g_numSessions;
    if(g_currentSession%sessionsTillLongBreak === 0) { //Long Break
        return g_longBreakLength;
    } else if(g_currentSession%2 === 0) { //Short Break
        return g_shortBreakLength;
    } else {
        return g_sessionLength;
    }
}

function updatePreferences() {
    g_sessionLength = g_sessionLengthInput.value*60*1000;
    g_shortBreakLength = g_shortBreakLengthInput.value*60*1000;
    g_longBreakLength = g_longBreakLengthInput.value*60*1000;
    g_numSessions = g_numSessionsInput.value*60*1000;
    g_currentSessionLength = updateCurrentSessionLength();
    if(g_paused || !g_initialized) {
        if(isNaN(g_currentSessionLength)) {
            g_currentSessionLength = g_sessionLength;
        }
        if(isNaN( g_elapsedTime)) {
            g_elapsedTime = 0;
        }
        render(g_elapsedTime, g_currentSessionLength);
    }
}

function update() {
    let now = new Date();
    g_currentTime = now.getTime();
    let elapsedTime = g_currentTime - g_startTime + g_elapsedTime;
    if(g_currentSessionLength - elapsedTime <= 0) {
        g_currentSessionLength = nextSession();
        render(0, g_currentSessionLength)
    } else {
        render(elapsedTime, g_currentSessionLength);
    }
}

function render(elapsedTime, currentSessionLength) {
    g_ctx.clearRect(0, 0, g_canvas.width, g_canvas.height);
    g_ctx.save();
    g_ctx.textAlign = "center";
    g_ctx.font = "Bold 40px Arial";
    let secondsRemaining = Math.floor((currentSessionLength - elapsedTime)/1000)%60;
    if(secondsRemaining < 10) { secondsRemaining = "0" + secondsRemaining; }
    let minutesRemaining = Math.floor((currentSessionLength - elapsedTime)/60000)%60;
    if(minutesRemaining < 10) { minutesRemaining = "0" + minutesRemaining; }
    let hoursRemaining = Math.floor((currentSessionLength - elapsedTime)/3600000);
    if(hoursRemaining < 10) { hoursRemaining = "0" + hoursRemaining; }
    g_ctx.fillText(hoursRemaining + ":" + minutesRemaining + ":" + secondsRemaining, g_canvas.width/2, g_canvas.height/2);
    g_ctx.restore();
}
//initialize
function init() {
    if(g_initialized) {
        resumeTimer();
    } else {
        startTimer();
        //call update 30 times per second
        g_intervalID = window.setInterval(update, 33.333);
        g_initialized = true;
        if(isNaN(g_currentSessionLength)) {
            g_currentSessionLength = g_sessionLength;
        }
    }
}

updatePreferences();
render(0, g_sessionLength);