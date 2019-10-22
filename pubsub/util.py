import subprocess
import threading

def monitor_command(command, cwd, timeout=0):
    """
    Executes a command-line instruction, with a specified timeout (or 0 for no timeout)
    Returns (exitcode, stdout, stderr) upon completion, or (-1, '', '') if timeout
    """
    subproc = subprocess.Popen(command, stdout=subprocess.PIPE, stderr=subprocess.PIPE, cwd=cwd)
    if timeout > 0:
        to = threading.Timer(timeout, subproc.kill)
        try:
            to.start()
            proc_stdout, proc_stderr = subproc.communicate()
            return (subproc.returncode, proc_stdout, proc_stderr)
        finally:
            to.cancel()
    else:
        proc_stdout, proc_stderr = subproc.communicate()
        return (subproc.returncode, proc_stdout, proc_stderr)
    return (-1, '', '')