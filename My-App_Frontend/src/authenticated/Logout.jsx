export const sendLogout = (props) => {

    fetch('/api/logout', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${localStorage.getItem('token')}` 
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            localStorage.removeItem('token'); 
            console.log('Logout successful, Token removed:', data);
        })
        .catch(error => {
            console.error('Logout failed:', error);
            alert('Logout failed. Please try again.');
        });

}
