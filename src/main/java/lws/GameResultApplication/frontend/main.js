document.addEventListener('DOMContentLoaded', function() {
    const editButtons = document.querySelectorAll('.edit-btn');
    const detailBtns = document.querySelectorAll('.view-detail-btn');
    const deleteButtons = document.querySelectorAll('.btn-danger');
    const playerDetailsSection = document.getElementById('player-details');
    const baseUrl = 'http://localhost:8080';


    // Event Listener Edit Button
    editButtons.forEach(function(button) {
        button.addEventListener('click', function() {
            const listItem = this.closest('.list-group-item');
            const editFields = listItem.querySelector('.edit-fields');

            // Toggle visibility of edit fields
            if (editFields.style.display === 'none' || editFields.style.display === '') {
                editFields.style.display = 'block';
                
                // Create and display the "Save" button
                let saveButton = document.createElement('button');
                saveButton.textContent = 'Save';
                saveButton.classList.add('btn', 'btn-primary', 'save-btn', 'mt-2');
                listItem.appendChild(saveButton);

                // Event Listener Save Button
                saveButton.addEventListener('click', function() {
                    const newName = editFields.querySelector('input[type="text"]').value.trim();
                    const newHighscore = editFields.querySelector('input[type="text"]').value.trim();
                    const newEmail = editFields.querySelector('input[type="email"]').value.trim();
                    const playerId = listItem.querySelector('.player-id').textContent.trim();

                    // Update URL with player ID
                    const url = new URL('http://localhost:5500/GameResultApplication/players.html');
                    const params = new URLSearchParams(url.search);
                    params.set('playerId', playerId); // Corrected to set 'playerId' parameter
                    url.search = params.toString();
                    history.replaceState(null, null, url.toString());

                    // UPDATE Method
                    fetch(`${baseUrl}/api/player/${playerId}`, {
                        method: 'PUT',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({
                            id: playerId,
                            name: newName,
                            email: newEmail,
                            highscore: newHighscore
                        })
                    })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('Network response was not ok ' + response.statusText);
                        }
                        return response.json();
                    })
                    .then(data => {
                        console.log('Erfolgreich aktualisiert:', data);

                        // Update the player's name, email, and highscore in the list item
                        listItem.querySelector('.player-name').textContent = newName;
                        listItem.querySelector('.player-email').textContent = newEmail;
                        listItem.querySelector('.player-highscore').textContent = newHighscore;

                        // Hide the edit fields and remove the save button
                        editFields.style.display = 'none';
                        listItem.removeChild(saveButton);

                        // If the player details section is showing the updated player, update it as well
                        const currentId = document.getElementById('details-player-id').textContent;
                        if (currentId === playerId) {
                            document.getElementById('details-player-name').textContent = newName;
                            document.getElementById('details-player-email').textContent = newEmail;
                            document.getElementById('details-player-highscore').textContent = newHighscore;
                        }
                    })
                    .catch(error => {
                        console.error('Es gab ein Problem mit der Fetch-Operation:', error);
                    });
                });
            } else {
                // Hide the edit fields and remove the "Save" button if visible
                editFields.style.display = 'none';
                const saveButton = listItem.querySelector('.save-btn');
                if (saveButton) {
                    listItem.removeChild(saveButton);
                }
            }
        });
    });

    // Add click event listener for Details buttons
    detailBtns.forEach(function(detailBtn) {
        detailBtn.addEventListener('click', function() {
            const listItem = this.closest('.list-group-item');
            const playerId = listItem.querySelector('.player-id').textContent.trim();
            const playerName = listItem.querySelector('.player-name').textContent.trim();

            // Check if the same player is being clicked again to toggle visibility
            const currentId = document.getElementById('details-player-id').textContent;
            if (currentId === playerId && playerDetailsSection.style.display === 'block') {
                // Hide the player details section if the same player is clicked again
                playerDetailsSection.style.display = 'none';
            } else {
                // Update player details in the details section
                document.getElementById('details-player-id').textContent = playerId;
                document.getElementById('details-player-name').textContent = playerName;

                // Show the player details section
                playerDetailsSection.style.display = 'block';
            }
        });
    });

    // Add click event listener for Delete buttons
    deleteButtons.forEach(function(deleteBtn) {
        deleteBtn.addEventListener('click', function() {
            const listItem = this.closest('.list-group-item');
            const playerId = listItem.querySelector('.player-id').textContent.trim();
            const playerName = listItem.querySelector('.player-name').textContent.trim();

            // Confirm if the user wants to delete
            const confirmed = confirm(`Are you sure you want to delete the player: ${playerName}?`);
            if (confirmed) {
                fetch(`https://api.beispiel.com/data/${playerId}`, {
                    method: 'DELETE'
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok: ' + response.statusText);
                    }
                    console.log(`Player ${playerId} deleted successfully`);

                    const currentId = document.getElementById('details-player-id').textContent;
                    if (currentId === playerId) {
                        document.getElementById('details-player-id').textContent = '';
                        document.getElementById('details-player-name').textContent = '';
                        document.getElementById('details-player-email').textContent = '';
                        document.getElementById('details-player-highscore').textContent = '';
                        playerDetailsSection.style.display = 'none';
                    }
                    listItem.remove();
                })
                .catch(error => {
                    console.error('There was an issue with the DELETE request:', error);
                });
            }
        });
    });

    // GET
    fetch('https://api.beispiel.com/data')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            console.log(data);  // Process with data
        })
        .catch(error => {
            console.error('Es gab ein Problem mit der Fetch-Operation:', error);
        });

    // POST
    fetch('https://api.beispiel.com/data', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({  // Data, which should be send
            name: 'Neuer Spieler',
            email: 'spieler@beispiel.com'
        })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            console.log('Erfolgreich gesendet:', data);
        })
        .catch(error => {
            console.error('Es gab ein Problem mit der Fetch-Operation:', error);
        });


//Felix_Part
const snakeBtn = document.getElementById("btn-snake");
const tetrisBtn = document.getElementById("btn-tetris");
// const totalLeaderBtn = document.getElementById("btn-totalLeader");

const table = document.getElementById("leaderboard");
const snakeData = [
  { name: "Felix", score: "100" },
  { name: "Kris", score: "1" },
  { name: "Kris", score: "1" },
  { name: "Kris", score: "1" },
  { name: "Kris", score: "1" },
  { name: "Kris", score: "1" },
  { name: "Kris", score: "1" },
  { name: "Kris", score: "1" },
  { name: "Kris", score: "1" },
];
const tetrisData = [
  { name: "Felix", score: "100" },
  { name: "Felix", score: "100" },
  { name: "Felix", score: "100" },
  { name: "Felix", score: "100" },
  { name: "Felix", score: "100" },
  { name: "Felix", score: "100" },
  { name: "Kris", score: "1" },
  { name: "Kris", score: "1" },
  { name: "Kris", score: "1" },
  { name: "Kris", score: "1" },
  { name: "Kris", score: "1" },
  { name: "Kris", score: "1" },
];

let rowCounter = 1;
updateTable(snakeData);

snakeBtn.addEventListener("click", function () {
  updateTable(snakeData);
});
tetrisBtn.addEventListener("click", function () {
  updateTable(tetrisData);
});

function deleteTableContent() {
  while (table.rows.length != 1) {
    table.deleteRow(-1);
    rowCounter = 1;
  }
}

function updateTable(data) {
  deleteTableContent();
  data.slice(0, 10).forEach((row) => {
    const tableRow = table.insertRow();
    tableRow.innerHTML = `
      <td>${rowCounter++}</td>
      <td>${row.name}</td>
      <td>${row.score}</td>
      `;
  });
}

function getData() {
  fetch("https://api.example.com/data")
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response, json();
    })
    .then((data) => {
      console.log(data);
      jsonToObjects(data);
    });
}

function jsonToObjects(jsonString) {
  const data = JSON.parse(jsonString);
  if (!Array.isArray(data)) {
    throw new Error("Input data must be a JSON array");
  }
  const arrayOfObjects = data.map((item) => {
    if (typeof item !== "object" || item === null) {
      throw new Error("Elements in the JSON array must be objects");
    }
    const { name, score } = item;
    if (typeof name !== "string" || typeof score !== "number") {
      throw new Error(
        "Objects in the JSON array must have 'name' (string) and 'score' (number) properties"
      );
    }
    return { name, score };
  });

  return arrayOfObjects;
}


// Jannis Part
document.getElementById('playerForm').addEventListener('submit', function(event) {
    event.preventDefault();  // Verhindert das automatische Absenden des Formulars

    // Name und Email aus den Eingabefeldern auslesen
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;

    // Überprüfen, ob beide Felder ausgefüllt sind
    if (name && email) {
        // Daten in ein JSON-Objekt umwandeln
        const playerData = {
            name: name,
            email: email
        };
        
        // JSON-Daten in der Konsole anzeigen
        console.log('Player JSON:', JSON.stringify(playerData));

        // AJAX-Request an das Backend senden (Beispiel mit fetch)
        fetch('http://localhost:8080/api/player', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(playerData)
        })
        .then(response => response.json())
        .then(data => {
            console.log('Erfolg:', data);
            alert(`Spieler hinzugefügt:\nName: ${name}\nEmail: ${email}`);
        })
        .catch((error) => {
            console.error('Fehler:', error);
            alert('Fehler beim Hinzufügen des Spielers');
        });

        // Felder zurücksetzen
        document.getElementById('playerForm').reset();
    } else {
        alert('Bitte füllen Sie beide Felder aus.');
    }
});

document.getElementById('playerForm').addEventListener('submit', function(event) {
    event.preventDefault();  // Verhindert das automatische Absenden des Formulars

    // Name und Email aus den Eingabefeldern auslesen
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;

    // Überprüfen, ob beide Felder ausgefüllt sind
    if (name && email) {
        // Daten in ein JSON-Objekt umwandeln
        const playerData = {
            name: name,
            email: email
        };
        
        // JSON-Daten in der Konsole anzeigen
        console.log('Player JSON:', JSON.stringify(playerData));

        // AJAX-Request an das Backend senden (Beispiel mit fetch)
        fetch('http://localhost:8080/api/player', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(playerData)
        })
        .then(response => response.json())
        .then(data => {
            console.log('Erfolg:', data);
            alert(`Spieler hinzugefügt:\nName: ${name}\nEmail: ${email}`);
        })
        .catch((error) => {
            console.error('Fehler:', error);
            alert('Fehler beim Hinzufügen des Spielers');
        });

        // Felder zurücksetzen
        document.getElementById('playerForm').reset();
    } else {
        alert('Bitte füllen Sie beide Felder aus.');
    }
});



});
