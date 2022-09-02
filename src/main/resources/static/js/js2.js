function getGridSize() {
    let value = document.getElementById("form").length;
    // let ZombiePosition = document.getElementById("ZombieLocation").submit();
    // let CreatureList = document.getElementById("CreatureList").submit();
    // let ZombieAction = document.getElementById("gridSize").submit();
    document.getElementById("demo").innerHTML = value;
}

const container = document.querySelector('#demo');

function createBoxes(numBox) {
    container.style.gridTemplateColumns = `repeat(${numBox}, 1fr)`;
    for (let i = 0; i < numBox * numBox; i++) {
        const square = document.createElement('div');
        square.classList.add('box');
        container.appendChild(square);
    }
}

createBoxes(5);
// function getZombiePosition() {
//
//
// }
//
// function getCreatureList() {
//
//
// }
//
// function getZombieAction() {
//
//
// }
