import React, {useEffect, useState} from 'react';
import UserService from '../../services/UserService';


const LoginPage = () => {
    const [error, setError] = useState<string>('');


    useEffect(() => {
        UserService.getUsers()
            .then(response => setUsers(response))
            .catch(error => setError(error.message));
    }, []);

    return (
        <div>
            {error}
            HI
        </div>
    );
};

export default LoginPage;
