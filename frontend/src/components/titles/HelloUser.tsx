import React, { useEffect, useState } from 'react';
import { UserService } from '../../services/User/UserService';

interface HelloUserProps {
    userName?: string;
}

function HelloUser({ userName }: HelloUserProps) {
    const [name, setName] = useState(userName || '');

    useEffect(() => {
        const fetchUser = async () => {
            try {
                const response = await UserService.getUser();
                if (response && response.data) {
                    setName(response.data.name);
                }
            } catch (error) {
                console.error('Failed to fetch user:', error);
            }
        };

        fetchUser();
    }, []);

    return (
        <>
            <h1 className="text-3xl leading-10 text-gray-900 font-bold m-8">
                Hello, {name}!
            </h1>
        </>
    );
}

export default HelloUser;
