$("#button").click(function (){
    var gridSize = $("#gridSize").val();
    var zombieX = $("#zombieX").val();
    var zombieY = $("#zombieY").val();
    var command = $("#command").val();
    var creatureX1 = $("#creatureX1").val();
    var creatureY1 = $("#creatureY1").val();
    var creatureX2 = $("#creatureX2").val();
    var creatureY2 = $("#creatureY2").val();
    var creatureX3 = $("#creatureX3").val();
    var creatureY3 = $("#creatureY3").val();
    var str={
        "gridSize": gridSize,
        "zombie": {
            "x": zombieX,
            "y": zombieY
        },
        "creatures": [
            {
                "x": creatureX1,
                "y": creatureY1
            },
            {
                "x": creatureX2,
                "y": creatureY2
            },
            {
                "x": creatureX3,
                "y": creatureY3
            }
        ],
        "commands": command
    }
    $.ajax(
        {
            type:"post",
            url:"http://localhost:8080/getValue",
            data:{
                value:JSON.stringify(str)
            },
            dataType:"text",
            success:function (data) {
                console.log(data);
                $("#textarea").text(data);
            }
        }
    )
})

const container = document.querySelector('#demo');

function createBoxes() {
    container.style.gridTemplateColumns = `repeat(${numBox}, 1fr)`;
    for (let i = 0; i < numBox * numBox; i++) {
        const square = document.createElement('div');
        square.classList.add('box');
        container.appendChild(square);
    }
}
createBoxes();
