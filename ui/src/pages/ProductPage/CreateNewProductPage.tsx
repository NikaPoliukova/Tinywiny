import React, {useState} from 'react';
import Modal from "react-bootstrap/Modal";
import {Button, Form} from "react-bootstrap";


const CreateDevice = () => {
    const [rpoduct] = useState()
    const [name, setName] = useState('')
    const [price, setPrice] = useState(0)
    const [file, setFile] = useState(null)
    const [info, setInfo] = useState([])


    const addInfo = () => {


        const addDevice = () => {
            const formData = new FormData()
            formData.append('name', name)
            formData.append('price', `${price}`)


            return (
                <Modal

                >
                    <Modal.Header closeButton>
                        <Modal.Title id="contained-modal-title-vcenter">
                            Добавить устройство
                        </Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form>

                            <Form.Control
                                value={name}
                                onChange={e => setName(e.target.value)}
                                className="mt-3"
                                placeholder="Введите название устройства"
                            />
                            <Form.Control
                                value={price}
                                onChange={e => setPrice(Number(e.target.value))}
                                className="mt-3"
                                placeholder="Введите стоимость устройства"
                                type="number"
                            />
                            <Form.Control
                                className="mt-3"
                                type="file"

                            />
                            <hr/>
                            <Button
                                variant={"outline-dark"}
                                onClick={addInfo}
                            >
                                Добавить новое свойство
                            </Button>

                        </Form>
                    </Modal.Body>
                    <Modal.Footer>

                        <Button variant="outline-success" onClick={addDevice}>Добавить</Button>
                    </Modal.Footer>
                </Modal>
            );
        }
    }
}


