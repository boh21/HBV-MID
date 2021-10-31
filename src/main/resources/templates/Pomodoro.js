"use strict";

//Get Canvas to display on
var g_canvas = document.getElementById("myCanvas");
var g_ctx = g_canvas.getContext("2d");
//Time Tracking
var g_startTime;
var g_currentTime;

function startTimer() {
    let now = new Date();
    g_startTime = now.getTime();
    g_currentTime = now.getTime();
}

function update() {
    let now = new Date();
    g_currentTime = now.getTime();
    let elapsedTime = g_currentTime - g_startTime;
    render(elapsedTime);
}

function render(elapsedTime) {
    g_ctx.clearRect(0, 0, g_canvas.width, g_canvas.height);
    g_ctx.save();
    g_ctx.textAlign = "center";
    g_ctx.font = "Bold 40px Arial";
    let secondsElasped = Math.floor(elapsedTime/1000)
    g_ctx.fillText(secondsElasped, g_canvas.width/2, g_canvas.height/2);
    g_ctx.restore();
}

//initialize
startTimer();

//call update 30 times per second
var intervalID = window.setInterval(update, 33.333);
