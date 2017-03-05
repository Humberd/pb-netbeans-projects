var canvas = document.getElementById("myCanvas");
var ctx = canvas.getContext("2d");
var bazaZdjec = [];
var WIDTH = canvas.width;
var HEIGHT = canvas.height;
var middleImage = 1;
var nextButton;
var prevButton;
function drawImages() {
	ctx.save();
	ctx.globalAlpha = 1;
	for (var i = middleImage-1, counter = 0, offset = 15; i<=middleImage+1; i++){
		if (bazaZdjec.length>i && i>=0){
			if (counter == 1) {
				ctx.drawImage(bazaZdjec[i].obrazek, counter*(WIDTH/4+WIDTH/8)-offset,3*HEIGHT/12-offset,WIDTH/4+2*offset,HEIGHT/12*8+2*offset);
			} else {
        ctx.drawImage(bazaZdjec[i].obrazek, counter*(WIDTH/4+WIDTH/8),3*HEIGHT/12+offset,WIDTH/4-offset,HEIGHT/12*8-2*offset);
			}
			
		}
		counter++;
	}
	ctx.restore();
	nextButton.draw();
	prevButton.draw();
}
function addZdjecie(src, adres) {
	var image = new Image();
	image.src = src;
	
	if (!adres)
		adres = "";

	var zdjecie = {
		obrazek: image,
		adresPrzekierowania: adres,
	};
	bazaZdjec.push(zdjecie);
}
//@param 
// x - wspolrzedna x
// y - wspolrzedna x
// width -szerokosc guzika
// height - wysokosc guzika
// text - wypisywanie tekstu
var button = function (x,y,width,height, text) {
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	if (!text)
		this.text = "Button";
	else
	  this.text = text;
	this.mouseClick = false;
	this.mouseOver = false;
	this.grd = ctx.createRadialGradient(this.x+this.width/2,this.y+this.height/2,1,this.x+this.width/2,this.y+this.height/2,100);
	this.grd.addColorStop(0,"grey");
	this.grd.addColorStop(1,"white");
		
	this.draw = function () {
		if (this.mouseOver) {
			 ctx.globalAlpha = 1;
		}
		else {
      if (ctx.globalAlpha <=1){
				ctx.globalAlpha =0.5;
			}
		}
		ctx.lineWidth = 2;
		ctx.fillStyle = this.grd;
		ctx.fillRect(this.x, this.y, this.width,this.height);
		ctx.strokeRect(this.x, this.y, this.width,this.height);
		ctx.font = HEIGHT/25+"px Arial";
		ctx.textBaseline = "middle";
		ctx.textAlign = "center";
		ctx.fillStyle = "black";
		ctx.fillText(this.text,this.x+this.width/2,this.y+this.height/2);
	};
};

function start() {
	addZdjecie("http://i.imgur.com/JeLntOK.jpg"); //dodaje zdjecia jakie chce
	addZdjecie("http://i.imgur.com/8bPh4OB.jpg");
	addZdjecie("http://i.imgur.com/wfZy0p7.jpg?1");
	addZdjecie("http://i.imgur.com/wfZy0p7.jpg?1");
	nextButton = new button(9*WIDTH/16,HEIGHT/12, 4*WIDTH/16,HEIGHT/12,"NASTĘPNY"); //dodaje guziki
// 	nextButton = new button(0,HEIGHT/12, 4*WIDTH/16,HEIGHT/12,"NASTĘPNY"); //dodaje guziki
	prevButton = new button(3*WIDTH/16,HEIGHT/12,4*WIDTH/16,HEIGHT/12,"POPRZEDNI");
	var time = setInterval(update,30);
}
function update() {
	ctx.clearRect(0, 0, WIDTH, HEIGHT);
  drawImages();
}
start();


$("#myCanvas").mousemove(function (e) {
	var clickX = e.pageX - this.offsetLeft;
	var clickY = e.pageY - this.offsetTop;
	
	if (clickX >= nextButton.x && clickX <= nextButton.x+nextButton.width && clickY >= nextButton.y && clickY <= nextButton.y+nextButton.height) {
		nextButton.mouseOver = true;
	} else {
		nextButton.mouseOver = false;
	}
	if (clickX >= prevButton.x && clickX <= prevButton.x+prevButton.width && clickY >= prevButton.y && clickY <= prevButton.y+prevButton.height) {
		prevButton.mouseOver = true;
	} else {
		prevButton.mouseOver = false;
	}	
});
$("#myCanvas").click(function (e) {
	var clickX = e.pageX - this.offsetLeft;
	var clickY = e.pageY - this.offsetTop;

	
	if (clickX >= nextButton.x && clickX <= nextButton.x+nextButton.width && clickY >= nextButton.y && clickY <= nextButton.y+nextButton.height) {
		if (middleImage < bazaZdjec.length-1){
			middleImage ++;
		}
	} else {
		
	}
	if (clickX >= prevButton.x && clickX <= prevButton.x+prevButton.width && clickY >= prevButton.y && clickY <= prevButton.y+prevButton.height) {
		if (middleImage > 0) {
			middleImage --;
		}
	} else {
		
	}	
});












