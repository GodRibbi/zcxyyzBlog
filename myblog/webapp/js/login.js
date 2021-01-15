/*
 * _(:з」∠)_
 * Created by Shuqiao Zhang in 2019.
 * https://zhangshuqiao.org
 */

/*
 * This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 */
window.addEventListener("load", () => {
	"use strict";

	if (!CSS.supports("clip-path", "circle(120px at center)")) {
		document.getElementById("stage").innerHTML = '<img src="../assets/screenshot-1.png">';
		return;
	}

	let apiPath = "https://live2d.fghrsh.net/api", state = 0,
		modelId = localStorage.getItem("modelId"),
		modelTexturesId = localStorage.getItem("modelTexturesId");
	if (modelId === null) {
		modelId = 1;
		modelTexturesId = 53;
	}
	loadModel(modelId, modelTexturesId);

	function loadModel(modelId, modelTexturesId) {
		localStorage.setItem("modelId", modelId);
		if (modelTexturesId === undefined) modelTexturesId = 0;
		localStorage.setItem("modelTexturesId", modelTexturesId);
		loadlive2d("live2d", `${apiPath}/get/?id=${modelId}-${modelTexturesId}`, null);
		console.log("live2d", `模型 ${modelId}-${modelTexturesId} 加载完成`);
		setTimeout(() => {
			coverPosition("80%");
			state = 2;
		}, 2000);
	}

	function loadRandModel() {
		const modelId = localStorage.getItem("modelId"),
			modelTexturesId = localStorage.getItem("modelTexturesId");
		fetch(`${apiPath}/rand_textures/?id=${modelId}-${modelTexturesId}`)
			.then(response => response.json())
			.then(result => {
				loadModel(modelId, result.textures.id);
				setTimeout(() => {
					state = 2;
					coverPosition("80%");
					document.getElementById("refresh").setAttribute("href", "javascript:refresh()");
				}, 1000);
			});
	}

	function loadOtherModel() {
		const modelId = localStorage.getItem("modelId");
		fetch(`${apiPath}/switch/?id=${modelId}`)
			.then(response => response.json())
			.then(result => {
				loadModel(result.model.id);
			});
	}

	function coverPosition(pos) {
		document.getElementById("cover").style.bottom = pos;
	}

	window.info = function () {
		fetch("https://v1.hitokoto.cn")
			.then(response => response.json())
			.then(result => {
				alert("「" + result.hitokoto + "」——" + result.from);
			});
	};

	window.refresh = function () {
		state = 0;
		coverPosition("10%");
		document.getElementById("refresh").setAttribute("href", "javascript:void(0)");
		setTimeout(loadRandModel, 1000);
	};

	document.getElementById("handle").addEventListener("click", () => {
		if (state === 1) {
			state = 2;
			coverPosition("80%");
		}
		else if (state === 2) {
			state = 1;
			coverPosition("20%");
		}
	});

	document.querySelector("input[type=password]").addEventListener("focus", () => {
		if (state === 2) {
			state = 1;
			coverPosition("20%");
		}
	});
	document.querySelector("input[type=password]").addEventListener("blur", () => {
		if (state === 1) {
			state = 2;
			coverPosition("80%");
		}
	});
	//登陆管理
	$("#forget-box").hide();
	$("#code-box").hide();
	$("#updatePassword-box").hide();
	$("#register-box").hide();
	var code = 0;
	var email = "";
	//修改密码
	window.updatePassword = function () {
		$.ajax({
			type: "POST",
			url: "http://localhost:8080/back-up/UpdatePassword",
			data: {
				useremail: email,
				password: $("#password").val()
			},
			contentType: "application/x-www-form-urlencoded",
			success: function (res, status) {
				if (res == "success") {
					console.log(res);
					$("#signin-box").show();
					$("#updatePassword-box").hide();
					alert("修改成功！");
				}
				else if (res == "fail") {
					alert("修改失败！");
				}
			},
			error: function (xhr, errorText, errorType) {
				if (xhr.status == 401) {
					//do something
				}
			},
			complete: function () {
				//do something
			}
		})
	}
	window.checkCode = function () {
		if (code == $("#code").val()) {
			$("#updatePassword-box").show();
			$("#code-box").hide();
		}
		else {
			alert("验证码错误");
		}

	}
	window.sendEmail = function () {
		var data = $("#useremail").val();
		sendEmailFun(data);
		$("#code-box").show();
		$("#forget-box").hide();
	}
	window.goToForget = function () {
		$("#forget-box").show();
		$("#signin-box").hide();
	}

	//注册
	window.goToRegister = function () {
		$("#signin-box").hide();
		$("#register-box").show();
	}
	window.register = function () {

		var data = $("input[name='code-register']").val();
		if (code != data) {
			alert("验证码错误");
			return;
		}
		$.ajax({
			type: "GET",
			url: "http://localhost:8080/back-up/CheckUserEmail",
			data: {
				useremail: data
			},
			contentType: "application/x-www-form-urlencoded",
			success: function (res, status) {
				if (res == "success") {
					alert("邮箱已存在！");
					return;
				}
				else if (res == "fail") {
					$.ajax({
						type: "POST",
						url: "http://localhost:8080/back-up/AddUser",
						data:
						{
							useremail: $('input[name="useremail-register"]').val(),
							password: $('input[name="password-register"]').val()
						},
						contentType: "application/x-www-form-urlencoded",
						success: function (res, status) {
							if (res == "success") {
								console.log(res);
								alert("注册成功！");
								$("#signin-box").show();
								$("#register-box").hide();
							}
							else if (res == "fail") {
								alert("注册失败！");
							}
						},
						error: function (xhr, errorText, errorType) {
							if (xhr.status == 401) {
								//do something
							}
						},
						complete: function () {
							//do something
						}
					})
				}
			},
			error: function (xhr, errorText, errorType) {
				if (xhr.status == 401) {
					//do something
				}
			},
			complete: function () {
				//do something
			}
		})



	}
	window.getCode = function () {
		var data = $("input[name='useremail-register']").val();
		console.log(data);
		if (data.length == 0) {
			alert("邮箱为空");
			return;
		}
		sendEmailFun(data);
	}

	//发送邮件
	function sendEmailFun(e) {
		alert("邮箱发送成功");
		$.ajax({
			type: "GET",
			url: "http://localhost:8080/back-up/SendEmail",
			data: {
				useremail: e
			},
			contentType: "application/x-www-form-urlencoded",
			success: function (res, status) {
				if (status == "success") {
					console.log(res);
					code = res;
					email = $("#useremail").val();
				}
			},
			error: function (xhr, errorText, errorType) {
				if (xhr.status == 401) {
					//do something
				}
			},
			complete: function () {
				//do something
			}
		})
	}

	//登陆
	window.signin = function () {
		$.ajax({
			type: "GET",
			url: "http://localhost:8080/back-up/CheckUser",
			data: $('#signin-box').serialize(),
			contentType: "application/x-www-form-urlencoded",
			success: function (res, status) {
				if (res == "success") {
					console.log(res);
					window.location.href = "index.html";
				}
				else if (res == "fail") {
					alert("密码错误！");
				}
			},
			error: function (xhr, errorText, errorType) {
				if (xhr.status == 401) {
					//do something
				}
			},
			complete: function () {
				//do something
			}
		})
	}
});
